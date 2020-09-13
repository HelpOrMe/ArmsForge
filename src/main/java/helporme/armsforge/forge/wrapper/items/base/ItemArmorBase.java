package helporme.armsforge.forge.wrapper.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.models.armor.ModelOBJBiped;
import helporme.armsforge.forge.wrapper.utils.INamed;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorBase extends ItemArmor implements INamed
{
    private static final String[] partNames = new String[] { "Helmet", "Chest", "Leggings", "Boots" };
    protected ModelInfo modelInfo;
    protected String name;
    protected ModelBiped modelBiped;

    public ItemArmorBase(ItemArmor.ArmorMaterial material, String name, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
        this.name = name;

        modelInfo = new ModelInfo(getTexturePath(), getModelPath());
        modelBiped = getModelBiped(armorType);

        setCreativeTab(ArmsForge.tab);
        setTextureName(Version.modid + ":" + getTexturePath());
        setUnlocalizedName(getName());
    }

    @SideOnly(Side.CLIENT)
    public String getModelPath()
    {
        return "items/armor/" + name;
    }

    @SideOnly(Side.CLIENT)
    public String getTexturePath()
    {
        return "items/armor/" + name;
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getModelBiped(int armorSlot)
    {
        ModelOBJBiped modelBiped = new ModelOBJBiped(modelInfo, 1, 0, 64, 64);
        modelBiped.selectArmorType(armorSlot);
        return modelBiped;
    }

    @Override
    public String getName()
    {
        return name + partNames[armorType];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        itemIcon = register.registerIcon(Version.modid + ":armor/" + name + partNames[armorType] + "Icon");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        return modelBiped;
    }
}