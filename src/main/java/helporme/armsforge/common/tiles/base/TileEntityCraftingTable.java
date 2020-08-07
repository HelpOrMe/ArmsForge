package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.items.IItemRecipe;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.IRecipeTableStateHandler;
import helporme.armsforge.api.recipes.hammer.HammerBlowPattern;
import helporme.armsforge.api.recipes.table.TablesShape;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.*;

public abstract class TileEntityCraftingTable extends TileEntityTable implements ICraftingTable
{
    private static final int radius = 9;
    protected static final Random random = new Random();

    protected boolean craftActive = false;
    protected EntityPlayer craftingPlayer;
    protected ICraftingTableRecipe recipe;

    protected Map<String, LinkedList<Vector3Int>> tablesPositionsByIngredients = new HashMap<>();
    protected List<ItemStack> ingredients = new ArrayList<>();
    protected int selectedIngredientIndex = 0;

    protected HammerType neededHammerType;
    protected float timeLeft;

    @Override
    public void onHammerBlow(IHammer hammer, EntityPlayer player)
    {
        if (canPlayerActivateCraft(player))
        {
            activateCraft(player);
        }
        else if (craftActive)
        {
            handleHammerBlow(hammer);
        }
    }

    public boolean canPlayerActivateCraft(EntityPlayer player)
    {
        if (!isCraftActive() && hasRecipe())
        {
            ICraftingTableRecipe recipe = getRecipe();
            return recipe.canPlayerActivateCraft(player) &&
                    isItemOnTableEqualsRecipeMainItem(recipe) &&
                    isTablesNearContainsRecipeIngredients(recipe) &&
                    recipe.isTablesShapeValid(getTablesShape());
        }
        return false;
    }

    @Override
    public boolean isCraftActive()
    {
        return craftActive;
    }

    protected boolean isItemOnTableEqualsRecipeMainItem(ICraftingTableRecipe recipe)
    {
        ItemStack mainItemStack = recipe.getMainItem();
        ItemStack itemStackOnTable = getItem();
        return hasItem() && itemStackOnTable.isItemEqual(mainItemStack) &&
                itemStackOnTable.stackSize == mainItemStack.stackSize;
    }

    protected boolean isTablesNearContainsRecipeIngredients(ICraftingTableRecipe recipe)
    {
        List<ItemStack> ingredients = recipe.getIngredients();

        for (ISupportTable supportTable : getSupportTablesNear())
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
        if (supportTable.hasItem())
        {
            ItemStack itemStackOnTable = supportTable.getItem();
            boolean hasIngredientOnTable = itemStackOnTable.isItemEqual(ingredient);
            boolean validSizeOnTable = itemStackOnTable.stackSize >= ingredient.stackSize;
            return hasIngredientOnTable && validSizeOnTable;
        }
        return false;
    }

    protected TablesShape getTablesShape()
    {
        return new TablesShape(this, getSupportTablesNear());
    }

    public void activateCraft(EntityPlayer player)
    {
        craftActive = true;

        recipe = getRecipe();
        craftingPlayer = player;
        ingredients = recipe.getIngredients();
        updateTablesPositionsByIngredients();
        setNextBlowPattern();

        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler) recipe;
            stateHandler.onCraftingStart();
        }
    }

    @Override
    public void updateEntity()
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

    protected void handleHammerBlow(IHammer hammer)
    {
        HammerType hammerType = hammer.getHammerType();
        if (neededHammerType != hammerType || timeLeft <= 0 || !canRemoveIngredientFromTable())
        {
            cancelCraft();
        }
        else if (!hasIngredients())
        {
            endCraft();
        }
        else
        {
            removeSelectedIngredientFromTable();
            setNextBlowPattern();
        }
    }

    protected boolean canRemoveIngredientFromTable()
    {
        ItemStack ingredient = ingredients.get(selectedIngredientIndex);
        String convertedIngredient = ItemStackHelper.convertItemStackToString(ingredient);
        ISupportTable tableWithIngredient = getTableWithIngredient(convertedIngredient);

        if (tableWithIngredient == null)
        {
            updateTablesPositionsByIngredients();
            tableWithIngredient = getTableWithIngredient(convertedIngredient);
            if (tableWithIngredient == null)
            {
                return false;
            }
        }

        ItemStack itemStackOnTable = tableWithIngredient.getItem();
        return tableWithIngredient.hasItem() && itemStackOnTable.isItemEqual(ingredient);
    }

    protected void removeSelectedIngredientFromTable()
    {
        ItemStack selectedIngredient = ingredients.get(selectedIngredientIndex);
        String convertedIngredient = ItemStackHelper.convertItemStackToString(selectedIngredient);

        ISupportTable table = getTableWithIngredient(convertedIngredient);
        table.decrStackSize(selectedIngredient.stackSize);

        tablesPositionsByIngredients.get(convertedIngredient).removeFirst();
        ingredients.remove(selectedIngredient);
    }

    protected ISupportTable getTableWithIngredient(String convertedIngredient)
    {
        LinkedList<Vector3Int> tablePositions = tablesPositionsByIngredients.get(convertedIngredient);
        Vector3Int tablePosition = tablePositions.getFirst();
        return (ISupportTable)worldObj.getTileEntity(tablePosition.x, tablePosition.y, tablePosition.z);
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
    }

    @Override
    public void cancelCraft()
    {
        clearCraftInfo();

        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler) recipe;
            stateHandler.onCraftingCanceled();
        }
    }

    protected void endCraft()
    {
        if (recipe instanceof IRecipeTableStateHandler)
        {
            IRecipeTableStateHandler stateHandler = (IRecipeTableStateHandler) recipe;
            stateHandler.onCraftingEnd();
        }
        setItem(recipe.getResultItem().copy());
        clearCraftInfo();
    }

    protected void clearCraftInfo()
    {
        craftActive = false;
        recipe = null;
        tablesPositionsByIngredients.clear();
        ingredients.clear();
        selectedIngredientIndex = 0;
        neededHammerType = null;
        timeLeft = 0;
    }

    protected void updateTablesPositionsByIngredients()
    {
        tablesPositionsByIngredients.clear();
        for (ISupportTable supportTable : getSupportTablesNear())
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
        String convertedIngredient = ItemStackHelper.convertItemStackToString(ingredient);
        if (!tablesPositionsByIngredients.containsKey(convertedIngredient))
        {
            tablesPositionsByIngredients.put(convertedIngredient, new LinkedList<>());
        }

        Vector3Int tablePosition = supportTable.getPosition();
        tablesPositionsByIngredients.get(convertedIngredient).add(tablePosition);
    }

    @Override
    public ISupportTable[] getSupportTablesNear()
    {
        Set<ISupportTable> tables = new HashSet<>();
        for (int x = xCoord - radius; x < xCoord + radius; x++)
        {
            for (int z = zCoord - radius; z < zCoord + radius; z++)
            {
                TileEntity tile = worldObj.getTileEntity(x, yCoord,  z);
                if (tile instanceof ISupportTable)
                {
                    tables.add((ISupportTable)tile);
                    Vector3Int supportTablePosition = new Vector3Int(x, yCoord, z);
                    tables.addAll(getSupportShelvesOver(supportTablePosition));
                }
            }
        }
        return tables.toArray(new ISupportTable[0]);
    }

    protected List<ISupportTable> getSupportShelvesOver(Vector3Int position)
    {
        List<ISupportTable> tables = new ArrayList<>();
        for (int y = 1; y < 3; y ++)
        {
            TileEntity tile = worldObj.getTileEntity(position.x, position.y + y, position.z);
            if (!(tile instanceof ISupportTable))
            {
                break;
            }
            tables.add((ISupportTable)tile);
        }
        return tables;
    }

    @Override
    public boolean hasRecipe()
    {
        return !isEmptyAt(1);
    }

    @Override
    public ItemStack getRecipeItem()
    {
        return getStackInSlot(1);
    }

    @Override
    public ICraftingTableRecipe getRecipe()
    {
        ItemStack recipeItemStack = getRecipeItem();
        IItemRecipe itemRecipe = (IItemRecipe)recipeItemStack.getItem();
        return itemRecipe.getRecipe(recipeItemStack);
    }

    @Override
    public void setRecipe(ItemStack recipe)
    {
        if (isRecipeValidForThisTable(recipe))
        {
            setInventorySlotContents(1, recipe);
            return;
        }
        throw new IllegalArgumentException("Invalid recipe type " + recipe.getItem().getClass());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IItemRecipe || slot == 1)
        {
            return isRecipeValidForThisTable(itemStack) && slot == 1;
        }
        return true;
    }

    protected boolean isRecipeValidForThisTable(ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IItemRecipe)
        {
            IItemRecipe recipeItem = (IItemRecipe)itemStack.getItem();
            ICraftingTableRecipe recipe = recipeItem.getRecipe(itemStack);
            CraftingTableType recipeCraftingTable = recipe.getCraftingTableType();
            return recipeCraftingTable.equals(getTableType());
        }
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return 2;
    }
}
