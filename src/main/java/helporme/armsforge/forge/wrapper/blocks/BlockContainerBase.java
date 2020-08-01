package helporme.armsforge.forge.wrapper.blocks;

import helporme.armsforge.common.registry.utils.INamed;
import helporme.armsforge.forge.wrapper.tiles.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockBase implements ITileEntityProvider, INamed
{
    protected BlockContainerBase(Material material, String name)
    {
        super(material, name);
    }

    public String getName()
    {
        return name;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityBase)
        {
            TileEntityBase tileEntityBase = (TileEntityBase)tile;
            tileEntityBase.onRemove();
        }
        world.removeTileEntity(x, y, z);
    }

    public abstract Class<? extends TileEntity> getTileClass();
}
