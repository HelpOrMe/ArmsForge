package helporme.armsforge.common.registry.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import helporme.armsforge.common.registry.utils.RecipesHelper;

public class WorkbanchRecipeList extends RecipeList
{
    @Override
    public void addDefault()
    {
        addSimpleBlockRecipes();
        addMasterAnvilRecipe();
    }

    public static void addSimpleBlockRecipes()
    {
        RecipesHelper.addCompressRecipes(
                ItemStackHelper.getItemStack("armsforge:MetalBlock"),
                ItemStackHelper.getItemStack("armsforge:MetalIngot"),
                9);
    }

    public static void addMasterAnvilRecipe()
    {
        GameRegistry.addRecipe(
                ItemStackHelper.getItemStack("armsforge:MasterAnvil"),
                "XYX", " X ", " Z ",
                ('X'), ItemStackHelper.getItemStack("armsforge:MetalBlock", 8),
                ('Y'), ItemStackHelper.getItemStack("minecraft:anvil"),
                ('Z'), ItemStackHelper.getItemStack("Thaumcraft:blockMagicalLog"));
    }
}
