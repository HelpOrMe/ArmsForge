package helporme.armsforge.common.registry;

import helporme.armsforge.common.registry.recipes.*;

public final class RecipesRegistry
{
    private static final RecipeList[] recipeLists = new RecipeList[]
            {
                    new WorkbanchRecipeList(),
                    new SmeltingRecipeList(),
                    new ArmorsmithRecipeList(),
                    new ArmorRecipeList()
            };

    public static void addDefaultRecipes()
    {
        for (RecipeList recipeList : recipeLists)
        {
            recipeList.addDefault();
        }
    }
}
