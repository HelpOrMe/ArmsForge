package helporme.armsforge.common.registry.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.recipes.RecipesArmorDyed;

public class ArmorRecipeList extends RecipeList
{
    @Override
    public void addDefault()
    {
        GameRegistry.addRecipe(new RecipesArmorDyed());
    }
}
