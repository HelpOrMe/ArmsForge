package helporme.armsforge.forge.wrapper.items;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.utils.INamed;
import net.minecraft.item.Item;

public class ItemBase extends Item implements INamed
{
    protected final String name;
    protected final String itemType;

    public ItemBase(String name, String itemType)
    {
        this.name = name;
        this.itemType = itemType;
        setCreativeTab(ArmsForge.tab);
        setUnlocalizedName(name);
        setTextureName(Version.modid + ":" + itemType + "/" + name);
    }

    public String getName()
    {
        return name;
    }
}
