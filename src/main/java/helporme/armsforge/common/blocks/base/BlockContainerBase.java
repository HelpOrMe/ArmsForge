package helporme.armsforge.common.blocks.base;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.registry.utils.INamed;
import helporme.armsforge.common.tiles.base.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer implements INamed
{
    protected String name;

    protected BlockContainerBase(Material material, String name)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setBlockName(name);
        setBlockTextureName(Version.modid + ":" + name);
    }

    public String getName()
    {
        return name;
    }

    public abstract Class<? extends TileEntity> getTileClass();

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityBase)
        {
            TileEntityBase tileEntityBase = (TileEntityBase)tile;
            tileEntityBase.onRemove();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
}
