package helporme.armsforge.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.items.base.ItemArmorBase;

public class HeavySegmantaArmor extends ItemArmorBase
{
    public HeavySegmantaArmor(int renderIndex, int armorType)
    {
        super(ArmorMaterial.IRON, "HeavySegmanta", renderIndex, armorType);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTexturePath()
    {
        return "items/armor/LorikaHamata";
    }
}
