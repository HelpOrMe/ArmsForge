package helporme.armsforge.common.registry.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.item.ItemStack;

public final class RecipesHelper
{
    public static void addCompressRecipes(ItemStack metal, ItemStack nugget, int maxMeta)
    {
        for (int meta = 0; meta < maxMeta; meta++)
        {
            metal = ItemStackHelper.clone(metal, 1, meta);
            nugget = ItemStackHelper.clone(nugget, 1, meta);
            GameRegistry.addShapelessRecipe(metal, (Object[])ItemStackHelper.repeatItemStack(nugget, 9));
            nugget = ItemStackHelper.clone(nugget, 9);
            GameRegistry.addShapelessRecipe(nugget, metal);
        }
    }
}
