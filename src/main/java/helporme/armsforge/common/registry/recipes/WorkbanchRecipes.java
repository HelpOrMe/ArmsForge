package helporme.armsforge.common.registry.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.registry.RegistryList;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import helporme.armsforge.common.registry.utils.RecipesHelper;

public class WorkbanchRecipes extends RecipesList
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
                ItemStackHelper.getOwnBlockStack("MetalBlock"),
                ItemStackHelper.getOwnItemStack("MetalIngot"),
                9);
    }

    public static void addMasterAnvilRecipe()
    {
        GameRegistry.addRecipe(
                ItemStackHelper.getOwnBlockStack("MasterAnvil"),
                "XYX", " X ", " Z ",
                ('X'), ItemStackHelper.getOwnBlockStack("MetalBlock", 8),
                ('Y'), ItemStackHelper.getBlockStack("minecraft:anvil"),
                ('Z'), ItemStackHelper.getBlockStack("Thaumcraft:blockMagicalLog"));
    }
}
