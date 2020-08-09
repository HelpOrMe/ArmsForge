package helporme.armsforge.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.models.ModelArmorSuite;
import helporme.armsforge.forge.wrapper.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.ModelOBJBiped;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class TestArmor extends ItemArmor
{
    protected IIcon iconHelmet;
    protected IIcon iconChest;

    public TestArmor(int renderIndex, int armorType)
    {
        super(ArmorMaterial.IRON, renderIndex, armorType);
        setCreativeTab(ArmsForge.tab);
        setTextureName(Version.modid + ":armor/TestArmor");
        setUnlocalizedName("TestArmor");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.iconHelmet = register.registerIcon(Version.modid + ":armor/TestArmor");
        this.iconChest = register.registerIcon(Version.modid + ":armor/TestArmor");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return iconHelmet;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        ModelArmorSuite suite = new ModelArmorSuite(
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/Head.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/Body.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/RightArm.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/LeftArm.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/RightLeg.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/LeftLeg.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/RightBoot.obj"),
                new ModelInfo("items/armor/TestArmor.png", "items/armor/test/LeftBoot.obj"));
        ModelOBJBiped modelOBJBiped = new ModelOBJBiped(suite, 1, 0, 64, 64);
        modelOBJBiped.selectArmorType(armorSlot);
        return modelOBJBiped;
    }
}
