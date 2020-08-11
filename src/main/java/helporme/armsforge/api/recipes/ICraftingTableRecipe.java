package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.recipes.table.TablesShape;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface ICraftingTableRecipe
{
    String getName();

    CraftingTableType getCraftingTableType();

    boolean isTablesShapeValid(TablesShape tablesShape);

    boolean canPlayerActivateCraft(EntityPlayer player);

    ItemStack getMainItem();

    List<ItemStack> getIngredients();

    ItemStack getResultItem();
}
