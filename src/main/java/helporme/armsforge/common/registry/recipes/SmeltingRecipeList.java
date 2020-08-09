package helporme.armsforge.common.registry.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;

public class SmeltingRecipeList extends RecipeList
{
    @Override
    public void addDefault()
    {
        GameRegistry.addSmelting(
                ItemStackHelper.getItemStack("minecraft:iron_block"),
                ItemStackHelper.getItemStack("armsforge:MetalIngot", 7),
                10
        );
        GameRegistry.addSmelting(
                ItemStackHelper.getItemStack("armsforge:MetalBlock", 7),
                ItemStackHelper.getItemStack("armsforge:MetalIngot", 8),
                100
        );
    }
}
