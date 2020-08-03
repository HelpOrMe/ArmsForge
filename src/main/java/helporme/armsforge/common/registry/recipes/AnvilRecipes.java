package helporme.armsforge.common.registry.recipes;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tables.CraftingTableTypes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AnvilRecipes extends RecipesList
{
    @Override
    public void addDefault()
    {
        ArmsForgeApi.addCraftingTableRecipes(
                CraftingTableTypes.masterAnvil, new ItemStack(Items.cake), new ItemStack(Items.coal));
    }
}
