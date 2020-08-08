package helporme.armsforge.common.tiles.logic;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tiles.ICraftingTable;
import helporme.armsforge.api.blocks.tiles.ISupportTable;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.IRecipeTableStateHandler;
import helporme.armsforge.api.recipes.hammer.HammerBlowPattern;
import helporme.armsforge.api.recipes.table.TablesShape;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.network.fx.PacketCanceledFX;
import helporme.armsforge.common.core.network.fx.PacketItemFX;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.*;

public class CraftingLogic
{
    protected ICraftingTable craftingTable;
    protected TileEntity craftingTableTile;

    protected final Random random = new Random();

    public boolean craftActive = false;
    public ICraftingTableRecipe recipe;

    protected Map<String, LinkedList<Vector3Int>> tablesPositionsByIngredients = new HashMap<>();
    public List<ItemStack> ingredients = new ArrayList<>();
    public int selectedIngredientIndex = 0;

    public HammerType neededHammerType;
    public float timeLeft;

    public CraftingLogic(ICraftingTable craftingTable)
    {
        this.craftingTable = craftingTable;
        craftingTableTile = (TileEntity)craftingTable;
    }

    public void onHammerBlow(ItemStack hammerStack, EntityPlayer player)
    {
        if (canPlayerActivateCraft(player))
        {
            activateCraft();
        }
        else if (craftActive)
        {
            handleHammerBlow(hammerStack, player);
        }
    }

    public boolean canPlayerActivateCraft(EntityPlayer player)
    {
        if (!craftActive && !craftingTable.isEmptyInSlot(craftingTable.getRecipeSlot()))
        {
            ICraftingTableRecipe recipe = craftingTable.getRecipe();
            return recipe.canPlayerActivateCraft(player) &&
                    isItemOnTableEqualsRecipeMainItem(recipe) &&
                    isTablesNearContainsRecipeIngredients(recipe) &&
                    recipe.isTablesShapeValid(getTablesShape());
        }
        return false;
    }

    protected boolean isItemOnTableEqualsRecipeMainItem(ICraftingTableRecipe recipe)
    {
        ItemStack mainItem = recipe.getMainItem();
        ItemStack itemOnTable = craftingTable.getStackInSlot(craftingTable.getItemSlot());

        return !craftingTable.isEmptyInSlot(craftingTable.getItemSlot())
                && itemOnTable.isItemEqual(mainItem) &&
                itemOnTable.stackSize == mainItem.stackSize;
    }

    protected boolean isTablesNearContainsRecipeIngredients(ICraftingTableRecipe recipe)
    {
        List<ItemStack> ingredients = recipe.getIngredients();

        for (ISupportTable supportTable : craftingTable.getSupportTablesNear())
        {
            for (ItemStack ingredient : ingredients)
            {
                if (isTableContainsIngredient(supportTable, ingredient))
                {
                    ingredients.remove(ingredient);
                    break;
                }
            }
        }
        return ingredients.size() == 0;
    }

    protected boolean isTableContainsIngredient(ISupportTable supportTable, ItemStack ingredient)
    {
        int itemSlot = supportTable.getItemSlot();
        if (!supportTable.isEmptyInSlot(itemSlot))
        {
            ItemStack itemOnTable = supportTable.getStackInSlot(itemSlot);
            boolean hasIngredientOnTable = itemOnTable.isItemEqual(ingredient);
            boolean validSizeOnTable = itemOnTable.stackSize >= ingredient.stackSize;
            return hasIngredientOnTable && validSizeOnTable;
        }
        return false;
    }

    protected TablesShape getTablesShape()
    {
        return new TablesShape(craftingTable,craftingTable.getSupportTablesNear());
    }

    public void activateCraft()
    {
        craftActive = true;

        recipe = craftingTable.getRecipe();
        ingredients = recipe.getIngredients();
        updateTablesPositionsByIngredients();
        setNextBlowPattern();

        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler)recipe;
            stateHandler.onCraftingStart();
        }
    }

    public void onUpdate()
    {
        if (craftActive)
        {
            timeLeft -= 0.05f;
            if (timeLeft <= 0)
            {
                cancelCraft();
            }
        }
    }

    protected void handleHammerBlow(ItemStack hammerAtHand, EntityPlayer player)
    {
        Item hammerItem = hammerAtHand.getItem();
        IHammer hammer = (IHammer)hammerItem;
        HammerType hammerType = hammer.getHammerType();
        ItemStack stackOnTable = craftingTable.getStackInSlot(craftingTable.getItemSlot());

        hammerAtHand.damageItem(1, player);

        if (neededHammerType != hammerType || !canRemoveIngredientFromTable())
        {
            hammerAtHand.damageItem(hammerAtHand.getMaxDamage() / 10, player);
            cancelCraft();
        }
        else if (!recipe.getMainItem().isItemEqual(stackOnTable))
        {
            cancelCraft();
        }
        else
        {
            removeSelectedIngredient();
            if (hasIngredients())
            {
                setNextBlowPattern();
            }
            else
            {
                endCraft();
            }
        }
    }

    protected boolean canRemoveIngredientFromTable()
    {
        ItemStack ingredient = ingredients.get(selectedIngredientIndex);
        String convertedIngredient = ItemStackHelper.convertItemToString(ingredient);
        ISupportTable table = getTableWithIngredient(convertedIngredient);

        if (table == null)
        {
            updateTablesPositionsByIngredients();
            table = getTableWithIngredient(convertedIngredient);
            if (table == null)
            {
                return false;
            }
        }

        int itemSlot = table.getItemSlot();
        ItemStack itemStackOnTable = table.getStackInSlot(itemSlot);
        return !table.isEmptyInSlot(itemSlot) && itemStackOnTable.isItemEqual(ingredient);
    }

    protected void updateTablesPositionsByIngredients()
    {
        tablesPositionsByIngredients.clear();
        for (ISupportTable supportTable : craftingTable.getSupportTablesNear())
        {
            for (ItemStack ingredient : ingredients)
            {
                if (isTableContainsIngredient(supportTable, ingredient))
                {
                    cacheSupportTableByIngredient(supportTable, ingredient);
                    break;
                }
            }
        }
    }

    protected void cacheSupportTableByIngredient(ISupportTable supportTable, ItemStack ingredient)
    {
        String convertedIngredient = ItemStackHelper.convertItemToString(ingredient);
        if (!tablesPositionsByIngredients.containsKey(convertedIngredient))
        {
            tablesPositionsByIngredients.put(convertedIngredient, new LinkedList<>());
        }

        Vector3Int tablePosition = supportTable.getPosition();
        tablesPositionsByIngredients.get(convertedIngredient).add(tablePosition);
    }

    protected void removeSelectedIngredient()
    {
        ItemStack selectedIngredient = ingredients.get(selectedIngredientIndex);
        String convertedIngredient = ItemStackHelper.convertItemToString(selectedIngredient);

        spawnRemoveIngredientParticle(convertedIngredient);

        ISupportTable table = getTableWithIngredient(convertedIngredient);
        table.decrStackSize(table.getItemSlot(), selectedIngredient.stackSize);

        tablesPositionsByIngredients.get(convertedIngredient).removeFirst();
        ingredients.remove(selectedIngredient);
    }

    protected void spawnRemoveIngredientParticle(String convertedIngredient)
    {
        Vector3Int tablePosition = tablesPositionsByIngredients.get(convertedIngredient).getFirst();

        Vector3 position = new Vector3(tablePosition.x + 0.5f, tablePosition.y + 1, tablePosition.z + 0.5f);
        Vector3 motion = new Vector3(1, 2, 1);
        PacketItemFX packet = new PacketItemFX(position, motion, convertedIngredient);
        ArmsForge.fxEngine.spawnParticle(packet, craftingTableTile);
    }

    protected ISupportTable getTableWithIngredient(String convertedIngredient)
    {
        if (tablesPositionsByIngredients.containsKey(convertedIngredient))
        {
            LinkedList<Vector3Int> tablePositions = tablesPositionsByIngredients.get(convertedIngredient);
            Vector3Int tablePosition = tablePositions.getFirst();
            World world = craftingTableTile.getWorldObj();
            return (ISupportTable)world.getTileEntity(tablePosition.x, tablePosition.y, tablePosition.z);
        }
        return null;
    }

    protected boolean hasIngredients()
    {
        return ingredients.size() > 0;
    }

    protected void setNextBlowPattern()
    {
        int randomIngredientIndex = random.nextInt(ingredients.size());
        ItemStack randomIngredient = ingredients.get(randomIngredientIndex);

        HammerBlowPattern[] patterns = ArmsForgeApi.getHammerBlowsPatternsForItem(randomIngredient);
        int randomPatternIndex = random.nextInt(patterns.length);
        HammerBlowPattern randomPattern = patterns[randomPatternIndex];

        neededHammerType = randomPattern.hammerType;
        timeLeft = randomPattern.time;
        craftingTable.markDirtyAndUpdate();
    }

    public void cancelCraft()
    {
        craftActive = false;
        clearCraftInfo();
        spawnCancelParticle();

        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler) recipe;
            stateHandler.onCraftingCanceled();
        }
    }

    protected void spawnCancelParticle()
    {
        float x = craftingTableTile.xCoord + 0.5f;
        float y = craftingTableTile.yCoord + 1;
        float z = craftingTableTile.zCoord + 0.5f;

        Vector3 position = new Vector3(x, y, z);
        Vector3 motion = new Vector3(1, 2, 1);

        PacketCanceledFX packet = new PacketCanceledFX(position, motion);
        ArmsForge.fxEngine.spawnParticle(packet, craftingTableTile);
    }

    protected void endCraft()
    {
        craftActive = false;
        ItemStack resultItem = recipe.getResultItem().copy();
        craftingTable.setInventorySlotContents(craftingTable.getItemSlot(), resultItem);
        clearCraftInfo();

        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler)recipe;
            stateHandler.onCraftingEnd();
        }
    }

    protected void clearCraftInfo()
    {
        recipe = null;
        tablesPositionsByIngredients.clear();
        ingredients.clear();
        selectedIngredientIndex = 0;
        neededHammerType = null;
        timeLeft = 0;
    }
}
