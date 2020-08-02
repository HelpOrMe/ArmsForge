package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.ItemScrewdriver;

public class ToolsList extends ItemsList
{
    @Override
    public void createDefault()
    {
        addItems(
                new ItemScrewdriver()
        );
    }
}
