package helporme.armsforge.forge.wrapper.items.base;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.utils.INamed;
import net.minecraft.item.Item;

public class ItemBase extends Item implements INamed
{
    protected final String name;

    public ItemBase(String name)
    {
        this(name, Version.modid + ":" + name);
    }

    public ItemBase(String name, String iconTexturePath)
    {
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setUnlocalizedName(name);
        setTextureName(iconTexturePath);
    }

    public String getName()
    {
        return name;
    }
}
