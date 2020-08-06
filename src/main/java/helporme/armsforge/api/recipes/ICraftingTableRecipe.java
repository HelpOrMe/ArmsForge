package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.recipes.table.TablesShape;
import net.minecraft.item.ItemStack;

public interface ICraftingTableRecipe
{
    String getName();

    CraftingTableType getCraftingTableType();

    boolean isTablesShapeValid(TablesShape tablesShape);

    ItemStack[] getIngredients();

    ItemStack getResultItem();
}
