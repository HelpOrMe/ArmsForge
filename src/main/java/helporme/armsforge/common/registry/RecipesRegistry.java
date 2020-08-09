package helporme.armsforge.common.registry;

import helporme.armsforge.common.registry.recipes.ArmorsmithRecipeList;
import helporme.armsforge.common.registry.recipes.RecipeList;
import helporme.armsforge.common.registry.recipes.SmeltingRecipeList;
import helporme.armsforge.common.registry.recipes.WorkbanchRecipeList;

public final class RecipesRegistry
{
    private static final RecipeList[] recipeLists = new RecipeList[]
            {
                    new WorkbanchRecipeList(),
                    new SmeltingRecipeList(),
                    new ArmorsmithRecipeList()
            };

    public static void addDefaultRecipes()
    {
        for (RecipeList recipeList : recipeLists)
        {
            recipeList.addDefault();
        }
    }
}
