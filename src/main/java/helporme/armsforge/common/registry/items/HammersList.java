package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemHammer;
import helporme.armsforge.api.recipes.Hammers;

public class HammersList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemHammer("SmallhammerIron", Hammers.smallIron, 1000),
                new ItemHammer("SmallhammerSteel", Hammers.smallSteel, 4000),
                new ItemHammer("SmallhammerThaum", Hammers.smallThaum, 7000),

                new ItemHammer("MediumhammerIron", Hammers.mediumIron, 1000),
                new ItemHammer("MediumhammerSteel", Hammers.mediumSteel, 4000),
                new ItemHammer("MediumhammerThaum", Hammers.mediumThaum, 7000),

                new ItemHammer("BighammerIron", Hammers.bigIron, 1000),
                new ItemHammer("BighammerSteel", Hammers.bigSteel, 4000),
                new ItemHammer("BighammerThaum", Hammers.bigThaum, 7000)
        );
    }
}
