package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.base.ItemHammerBase;
import helporme.armsforge.common.items.hammers.DefaultHammerTypes;

public class ToolsList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemHammerBase("SmallhammerIron", DefaultHammerTypes.smallIron),
                new ItemHammerBase("SmallhammerSteel", DefaultHammerTypes.smallSteel),
                new ItemHammerBase("SmallhammerThaum", DefaultHammerTypes.smallThaum),

                new ItemHammerBase("MediumhammerIron", DefaultHammerTypes.mediumIron),
                new ItemHammerBase("MediumhammerSteel", DefaultHammerTypes.mediumSteel),
                new ItemHammerBase("MediumhammerThaum", DefaultHammerTypes.mediumThaum),

                new ItemHammerBase("BighammerIron", DefaultHammerTypes.bigIron),
                new ItemHammerBase("BighammerSteel", DefaultHammerTypes.bigSteel),
                new ItemHammerBase("BighammerThaum", DefaultHammerTypes.bigThaum)
        );
    }
}
