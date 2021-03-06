package helporme.armsforge.common.registry.recipes;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tiles.table.CraftingTableTypes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArmorsmithRecipeList extends RecipeList
{
    @Override
    public void addDefault()
    {
        ArmsForgeApi.addCraftingTableRecipes(
                CraftingTableTypes.armssmithTable, new ItemStack(Items.cake),
                new ItemStack(Items.coal), new ItemStack(Items.coal), new ItemStack(Items.coal));
    }
}
