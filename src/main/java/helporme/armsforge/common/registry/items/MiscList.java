package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemRecipe;
import helporme.armsforge.common.items.ItemScrewdriver;

public class MiscList extends ItemsList
{
    @Override
    public void addDefault()
    {
        addItems(
                new ItemScrewdriver(),
                new ItemRecipe()
        );
    }
}
