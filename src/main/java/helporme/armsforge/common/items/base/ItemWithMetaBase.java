package helporme.armsforge.common.items.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemWithMetaBase extends ItemBase
{
    protected int maxMeta;

    public ItemWithMetaBase(String name, int maxMeta)
    {
        super(name);
        this.maxMeta = maxMeta;
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < maxMeta + 1; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }

    protected int correctMeta(int meta)
    {
        return Math.min(meta, maxMeta);
    }
}
