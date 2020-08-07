package helporme.armsforge.api.recipes;

public interface IRecipeTableStateHandler
{
    void onCraftingStart();

    void onCraftingEnd();

    void onCraftingCanceled();
}
