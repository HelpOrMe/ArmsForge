package helporme.armsforge.common.items;

import helporme.armsforge.api.items.misc.IDebugTool;
import helporme.armsforge.forge.wrapper.items.ItemToolBase;

public class ItemScrewdriver extends ItemToolBase implements IDebugTool
{
    public ItemScrewdriver(String name)
    {
        super(name);
        setMaxDamage(-1);
    }
}
