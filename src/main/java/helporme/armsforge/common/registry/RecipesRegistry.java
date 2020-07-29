package helporme.armsforge.common.registry;

import helporme.armsforge.common.registry.recipes.RecipeList;
import helporme.armsforge.common.registry.recipes.SmeltingRecipes;
import helporme.armsforge.common.registry.recipes.WorkbanchRecipes;

public final class RecipesRegistry
{
    private static RecipeList[] recipeLists = new RecipeList[]
            {
                    new WorkbanchRecipes(),
                    new SmeltingRecipes()
            };

    public static void createDefaultRecipes()
    {
        for (RecipeList recipeList : recipeLists)
        {
            recipeList.createDefault();
        }
    }
}
