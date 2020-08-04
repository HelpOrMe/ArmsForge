package helporme.armsforge.api.items;

import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import net.minecraft.item.ItemStack;

public interface IRecipeItem
{
    ICraftingTableRecipe getRecipe(ItemStack itemStack);
}
