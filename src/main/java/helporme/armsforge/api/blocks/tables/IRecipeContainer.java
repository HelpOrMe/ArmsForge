package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.items.IItemRecipe;
import net.minecraft.item.ItemStack;

public interface IRecipeContainer
{
    boolean hasRecipe();

    IItemRecipe getRecipe();

    void setRecipe(ItemStack recipe);
}
