package helporme.armsforge.api.blocks.tiles;

import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import net.minecraft.item.ItemStack;

public interface IRecipeContainer
{
    int getRecipeSlot();

    ICraftingTableRecipe getRecipe();

    boolean isRecipeValid(ItemStack itemStack);
}
