package helporme.armsforge.common.core.registry;

import helporme.armsforge.common.core.registry.recipes.RecipeList;
import helporme.armsforge.common.core.registry.recipes.SmeltingRecipes;
import helporme.armsforge.common.core.registry.recipes.WorkbanchRecipes;

public final class RecipesRegistry
{
    private static RecipeList[] recipeLists = new RecipeList[]
            {
                    new WorkbanchRecipes(),
                    new SmeltingRecipes()
            };

    public static void addDefaultRecipes()
    {
        for (RecipeList list : recipeLists)
        {
            list.addDefaultRecipes();
        }
    }
}
