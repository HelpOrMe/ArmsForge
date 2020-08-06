package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import helporme.armsforge.api.recipes.table.TablesShape;
import net.minecraft.item.ItemStack;

public class ShapelessCraftingTableRecipe implements ICraftingTableRecipe
{
    protected String name;
    protected CraftingTableType craftingTableType;
    protected ItemStack result;
    protected ItemStack[] ingredients;

    public ShapelessCraftingTableRecipe(CraftingTableType craftingTableType,
                                        ItemStack result, ItemStack... ingredients)
    {
        this(craftingTableType, result.getItem().getUnlocalizedName(), result, ingredients);
    }

    public ShapelessCraftingTableRecipe(CraftingTableType craftingTableType,
                                        String name,ItemStack result, ItemStack... ingredients)
    {
        this.name = name;
        this.craftingTableType = craftingTableType;
        this.result = result;
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
    public ItemStack[] getIngredients()
    {
        return ingredients;
    }

    protected boolean isSupportTableContains(ISupportTable supportTable, ItemStack ingredient)
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

    @Override
    public ItemStack getResultItem()
    {
        return result;
    }
}
