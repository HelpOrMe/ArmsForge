package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;

import java.util.Set;

public interface ICraftingTable extends ITable, IRecipeContainer
{
    CraftingTableType getTableType();

    void onHammerBlow(IHammer hammer);

    boolean isCraftActive();

    void activateCraft();

    void selectRecipe(ICraftingTableRecipe recipe);

    ISupportTable[] getSupportTablesNear();
}
