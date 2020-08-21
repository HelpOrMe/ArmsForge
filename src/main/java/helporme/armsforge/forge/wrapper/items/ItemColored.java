package helporme.armsforge.forge.wrapper.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class ItemColored extends ItemWithMeta
{
    protected final int[] colors;

    public ItemColored(String name, String itemType, int[] colors)
    {
        super(name, itemType, colors.length);
        this.colors = colors;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass)
    {
        return colors[correctMeta(stack.getItemDamage())];
    }
}
