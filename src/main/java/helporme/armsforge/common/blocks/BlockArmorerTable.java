package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockModelBase;
import helporme.armsforge.common.tiles.TileEntityArmorerTable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorerTable extends BlockModelBase
{
    public BlockArmorerTable()
    {
        super(Material.iron, "ArmorerTable");
        setHardness(10f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeMetal);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityArmorerTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityArmorerTable();
    }
}
