package helporme.armsforge.common.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class ItemColoredBase extends ItemWithMetaBase
{
    protected int[] colors;

    public ItemColoredBase(String name, int[] colors)
    {
        super(name, colors.length - 1);
        this.colors = colors;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int p_82790_2_)
    {
        int meta = correctMeta(stack.getItemDamage());
        return colors[meta];
    }
}
