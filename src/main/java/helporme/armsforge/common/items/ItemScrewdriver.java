package helporme.armsforge.common.items;

import helporme.armsforge.api.items.IDebugTool;
import helporme.armsforge.forge.wrapper.items.ItemTool;

public class ItemScrewdriver extends ItemTool implements IDebugTool
{
    public ItemScrewdriver()
    {
        super("Screwdriver", "tools");
    }
}
