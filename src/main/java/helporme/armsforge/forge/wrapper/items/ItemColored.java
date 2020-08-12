package helporme.armsforge.forge.wrapper.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class ItemColored extends ItemWithMeta
{
    protected final int[] colors;

    public ItemColored(String name, String iconTexturePath, int[] colors)
    {
        super(name, iconTexturePath, colors.length);
        this.colors = colors;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int p_82790_2_)
    {
        return colors[correctMeta(stack.getItemDamage())];
    }
}
