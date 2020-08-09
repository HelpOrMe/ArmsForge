package helporme.armsforge.forge.wrapper.items;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;

public class ItemSword extends net.minecraft.item.ItemSword implements INamed
{
    protected final String name;
    protected final String itemType;

    public ItemSword(String name, ToolMaterial material)
    {
        super(material);
        this.name = name;
        this.itemType = "weapons";
        setCreativeTab(ArmsForge.tab);
        setUnlocalizedName(name);
        setTextureName(Version.modid + ":" + itemType + "/" + name);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
