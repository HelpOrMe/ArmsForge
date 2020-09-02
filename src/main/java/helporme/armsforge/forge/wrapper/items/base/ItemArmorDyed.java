package helporme.armsforge.forge.wrapper.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.render.models.armor.ModelOBJBiped;
import helporme.armsforge.forge.wrapper.render.models.armor.ModelOBJBipedDyed;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemArmorDyed extends ItemArmorBase
{
    public ItemArmorDyed(ArmorMaterial material, String name, int renderIndex, int armorType) {
        super(material, name, renderIndex, armorType);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getModelBiped(int armorSlot)
    {
        ModelOBJBiped modelBiped = new ModelOBJBipedDyed(modelInfo, 1, 0, 64, 64);
        modelBiped.selectArmorType(armorSlot);
        return modelBiped;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        ModelOBJBipedDyed modelDyed = (ModelOBJBipedDyed)modelBiped;
        modelDyed.setColor(getColorFromItemStack(itemStack, 0));
        return modelBiped;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass)
    {
        NBTTagCompound displayTagCompound = getColorTag(stack);
        if (displayTagCompound != null)
        {
            return displayTagCompound.getInteger("Color");
        }
        return 0xffffff;
    }

    public void setColor(ItemStack stack, int color)
    {
        NBTTagCompound displayTagCompound = getColorTag(stack);
        if (displayTagCompound != null)
        {
            displayTagCompound.setInteger("Color", color);
        }
        throw new IllegalArgumentException("Unable to set color from " + stack.toString());
    }

    protected NBTTagCompound getColorTag(ItemStack stack)
    {
        NBTTagCompound stackTagCompound = stack.getTagCompound();
        if (stackTagCompound != null && stackTagCompound.hasKey("display", 10))
        {
            NBTTagCompound displayTagCompound = stackTagCompound.getCompoundTag("display");
            if (displayTagCompound.hasKey("Color", 3))
            {
                return displayTagCompound;
            }
        }
        return null;
    }
}