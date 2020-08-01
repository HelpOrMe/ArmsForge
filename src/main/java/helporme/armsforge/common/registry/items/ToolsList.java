package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemHammer;
import helporme.armsforge.common.items.ItemScrewdriver;
import helporme.armsforge.common.registry.utils.HammerTypes;

public class ToolsList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemScrewdriver("Screwdriver")
        );
    }
}
