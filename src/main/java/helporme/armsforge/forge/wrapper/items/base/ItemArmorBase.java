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
    public static ModelInfo modelInfo;
    protected String name;

    public ItemArmorBase(ItemArmor.ArmorMaterial material, String name, int renderIndex, int armorType)
    {
        this(material, name, "armor", renderIndex, armorType);
    }

    public ItemArmorBase(ItemArmor.ArmorMaterial material, String name, String itemType, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
        this.name = name;

        setCreativeTab(ArmsForge.tab);
        setTextureName(Version.modid + ":items/" + itemType + "/" + name);
        setUnlocalizedName(getName());
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
        itemIcon = register.registerIcon(Version.modid + ":" + name + partNames[armorType] + "Icon");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        if (modelInfo == null)
        {
            modelInfo = new ModelInfo("items/armor/" + name, "items/armor/" + name);
        }
        ModelOBJBiped modelOBJBiped = new ModelOBJBiped(modelInfo, 1, 0, 64, 64);
        modelOBJBiped.selectArmorType(armorSlot);
        return modelOBJBiped;
    }
}