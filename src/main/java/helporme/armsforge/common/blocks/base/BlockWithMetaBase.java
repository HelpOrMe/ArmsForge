package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockWithMetaBase extends BlockBase
{
    @SideOnly(Side.CLIENT)
    protected int maxMeta;

    public BlockWithMetaBase(Material material, String name, int maxMeta)
    {
        super(material, name);
        this.maxMeta = maxMeta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < maxMeta + 1; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    protected int correctMeta(int meta)
    {
        return Math.min(meta, maxMeta);
    }
}
