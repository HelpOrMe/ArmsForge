package helporme.armsforge.common.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemWithSubTypesBase extends ItemBase
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] icons;
    protected int typesCount;

    public ItemWithSubTypesBase(String name, int typesCount)
    {
        super(name);
        this.typesCount = typesCount;
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return icons[damage];
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        icons = new IIcon[typesCount];
        for (int i = 0; i < typesCount; i++)
        {
            icons[i] = register.registerIcon(iconString + "_" + i);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < typesCount; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
