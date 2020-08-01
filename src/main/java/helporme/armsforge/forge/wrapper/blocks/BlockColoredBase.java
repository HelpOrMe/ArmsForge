package helporme.armsforge.forge.wrapper.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockColoredBase extends BlockWithMetaBase
{
    protected int[] colors;

    public BlockColoredBase(Material material, String name, int[] colors)
    {
        super(material, name, colors.length);
        this.colors = colors;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        return colors[correctMeta(meta)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return colors[correctMeta(world.getBlockMetadata(x, y, z))];
    }
}
