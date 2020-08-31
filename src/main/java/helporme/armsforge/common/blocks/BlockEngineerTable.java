package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.common.tiles.TileEntityEngineerTable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEngineerTable extends BlockTableBase
{
    public BlockEngineerTable()
    {
        super(Material.wood, "EngineerTable");
        setHardness(5f);
        setResistance(10f);
        setHarvestLevel("axe", 1);
        setStepSound(soundTypeWood);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityEngineerTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityEngineerTable();
    }
}
