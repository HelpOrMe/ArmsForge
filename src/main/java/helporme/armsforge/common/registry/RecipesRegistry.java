package helporme.armsforge.common.registry;

import helporme.armsforge.common.registry.recipes.AnvilRecipes;
import helporme.armsforge.common.registry.recipes.RecipesList;
import helporme.armsforge.common.registry.recipes.SmeltingRecipes;
import helporme.armsforge.common.registry.recipes.WorkbanchRecipes;

public final class RecipesRegistry
{
    private static final RecipesList[] recipeLists = new RecipesList[]
            {
                    new WorkbanchRecipes(),
                    new SmeltingRecipes(),
                    new AnvilRecipes()
            };

    public static void addDefaultRecipes()
    {
        for (RecipesList recipeList : recipeLists)
        {
            recipeList.addDefault();
        }
    }
}
