package helporme.armsforge.common.block;

import helporme.armsforge.common.block.base.BlockModelBase;
import helporme.armsforge.common.block.tiles.TileEntityArmorerTable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorerTable extends BlockModelBase
{
    public BlockArmorerTable()
    {
        super(Material.iron, "ArmorerTable");
        setHardness(15f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
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
