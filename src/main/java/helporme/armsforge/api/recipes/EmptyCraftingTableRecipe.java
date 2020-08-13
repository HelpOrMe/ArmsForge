package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.recipes.table.TablesShape;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EmptyCraftingTableRecipe implements ICraftingTableRecipe
{
    @Override
    public String getName()
    {
        return "empty";
    }

    @Override
    public CraftingTableType getCraftingTableType()
    {
        return new CraftingTableType("None", -1);
    }

    @Override
    public boolean isTablesShapeValid(TablesShape tablesShape)
    {
        return false;
    }

    @Override
    public boolean canPlayerActivateCraft(EntityPlayer player)
    {
        return false;
    }

    @Override
    public ItemStack getMainItem()
    {
        return new ItemStack(Items.carrot_on_a_stick);
    }

    @Override
    public List<ItemStack> getIngredients()
    {
        return new ArrayList<>();
    }

    @Override
    public ItemStack getResultItem()
    {
        return new ItemStack(Items.milk_bucket);
    }
}
