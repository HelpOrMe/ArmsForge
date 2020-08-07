package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import net.minecraft.item.ItemStack;

public interface IRecipeContainer
{
    boolean hasRecipe();

    ItemStack getRecipeItem();

    ICraftingTableRecipe getRecipe();

    void setRecipe(ItemStack recipe);

    void cancelCraft();
}
