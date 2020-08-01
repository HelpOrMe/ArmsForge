package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemHammer;
import helporme.armsforge.common.registry.utils.HammerTypes;

public class HammersList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemHammer("SmallhammerIron", HammerTypes.smallIron),
                new ItemHammer("SmallhammerSteel", HammerTypes.smallSteel),
                new ItemHammer("SmallhammerThaum", HammerTypes.smallThaum),

                new ItemHammer("MediumhammerIron", HammerTypes.mediumIron),
                new ItemHammer("MediumhammerSteel", HammerTypes.mediumSteel),
                new ItemHammer("MediumhammerThaum", HammerTypes.mediumThaum),

                new ItemHammer("BighammerIron", HammerTypes.bigIron),
                new ItemHammer("BighammerSteel", HammerTypes.bigSteel),
                new ItemHammer("BighammerThaum", HammerTypes.bigThaum)
        );
    }
}
