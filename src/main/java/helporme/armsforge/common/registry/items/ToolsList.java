package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemHammer;
import helporme.armsforge.common.registry.utils.DefaultHammerTypes;

public class ToolsList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemHammer("SmallhammerIron", DefaultHammerTypes.smallIron),
                new ItemHammer("SmallhammerSteel", DefaultHammerTypes.smallSteel),
                new ItemHammer("SmallhammerThaum", DefaultHammerTypes.smallThaum),

                new ItemHammer("MediumhammerIron", DefaultHammerTypes.mediumIron),
                new ItemHammer("MediumhammerSteel", DefaultHammerTypes.mediumSteel),
                new ItemHammer("MediumhammerThaum", DefaultHammerTypes.mediumThaum),

                new ItemHammer("BighammerIron", DefaultHammerTypes.bigIron),
                new ItemHammer("BighammerSteel", DefaultHammerTypes.bigSteel),
                new ItemHammer("BighammerThaum", DefaultHammerTypes.bigThaum)
        );
    }
}
