package helporme.armsforge.forge.wrapper.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.items.base.ItemBase;

public class ItemTool extends ItemBase
{
    public ItemTool(String name)
    {
        super(name, "tools");
    }

    public ItemTool(String name, String itemType)
    {
        super(name, itemType);
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
}
