package helporme.armsforge.common.registry.recipes;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tiles.CraftingTableTypes;
import helporme.armsforge.common.core.Version;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArmorsmithRecipes extends RecipesList
{
    @Override
    public void addDefault()
    {
        ArmsForgeApi.addRecipeIconNameToTable(Version.modid + ":" + "RecipeArmssmith", CraftingTableTypes.armssmithTable);
        ArmsForgeApi.addCraftingTableRecipes(
                CraftingTableTypes.armssmithTable, new ItemStack(Items.cake),
                new ItemStack(Items.coal), new ItemStack(Items.coal), new ItemStack(Items.coal));
    }
}
