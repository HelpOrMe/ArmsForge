package helporme.armsforge.forge.wrapper.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockWithMetaBase extends BlockBase
{
    protected int metaCount;

    public BlockWithMetaBase(Material material, String name, int metaCount)
    {
        super(material, name);
        this.metaCount = metaCount;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int meta = 0; meta < metaCount; meta++)
        {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    protected int correctMeta(int meta)
    {
        return Math.min(meta, metaCount - 1);
    }
}
