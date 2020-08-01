package helporme.armsforge.forge.wrapper.items;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.registry.utils.INamed;
import net.minecraft.item.Item;

public class ItemBase extends Item implements INamed
{
    protected String name;

    public ItemBase(String name)
    {
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setUnlocalizedName(name);
        setTextureName(Version.modid + ":" + name);
    }

    public String getName()
    {
        return name;
    }
}
