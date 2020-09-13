package helporme.armsforge.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.items.base.ItemArmorBase;

public class SegmantaArmor extends ItemArmorBase
{
    public SegmantaArmor(int renderIndex, int armorType)
    {
        super(ArmorMaterial.IRON, "Segmanta", renderIndex, armorType);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTexturePath()
    {
        return "items/armor/LorikaHamata";
    }
}
