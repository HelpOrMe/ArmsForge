package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tiles.CraftingTableType;
import helporme.armsforge.api.recipes.table.TablesShape;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapelessCraftingTableRecipe implements ICraftingTableRecipe
{
    protected String name;
    protected CraftingTableType craftingTableType;
    protected ItemStack result;
    protected ItemStack mainItem;
    protected ItemStack[] ingredients;

    public ShapelessCraftingTableRecipe(CraftingTableType craftingTableType,
                                        ItemStack result, ItemStack mainItem, ItemStack... ingredients)
    {
        this(craftingTableType, result.getItem().getUnlocalizedName(), result, mainItem, ingredients);
    }

    public ShapelessCraftingTableRecipe(CraftingTableType craftingTableType,
                                        String name, ItemStack result, ItemStack mainItem, ItemStack... ingredients)
    {
        this.name = name;
        this.craftingTableType = craftingTableType;
        this.result = result;
        this.mainItem = mainItem;
        this.ingredients = ingredients;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public CraftingTableType getCraftingTableType()
    {
        return craftingTableType;
    }

    @Override
    public boolean isTablesShapeValid(TablesShape tablesShape)
    {
        return true;
    }

    @Override
    public boolean canPlayerActivateCraft(EntityPlayer player)
    {
        return true;
    }

    @Override
    public ItemStack getMainItem()
    {
        return mainItem;
    }

    @Override
    public List<ItemStack> getIngredients()
    {
        return new ArrayList<>(Arrays.asList(ingredients));
    }

    @Override
    public ItemStack getResultItem()
    {
        return result;
    }
}
