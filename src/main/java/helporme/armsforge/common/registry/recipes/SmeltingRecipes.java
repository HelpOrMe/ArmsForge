package helporme.armsforge.common.registry.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;

public class SmeltingRecipes extends RecipeList
{
    @Override
    public void createDefault()
    {
        GameRegistry.addSmelting(
                ItemStackHelper.getBlockStack("minecraft:iron_block"),
                ItemStackHelper.getOwnItemStack("MetalIngot", 7),
                10
        );
        GameRegistry.addSmelting(
                ItemStackHelper.getOwnBlockStack("MetalBlock", 7),
                ItemStackHelper.getOwnItemStack("MetalIngot", 8),
                100
        );
    }
}
