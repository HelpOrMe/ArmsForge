package helporme.armsforge.forge.wrapper.items.base;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.utils.INamed;

public class ItemSword extends net.minecraft.item.ItemSword implements INamed
{
    protected final String name;

    public ItemSword(String name, ToolMaterial material)
    {
       this(name, "weapon", material);
    }

    public ItemSword(String name, String itemType, ToolMaterial material)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setUnlocalizedName(name);
        setTextureName(Version.modid + ":items/" + itemType + "/" + name);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
