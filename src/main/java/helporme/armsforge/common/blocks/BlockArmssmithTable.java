package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockCraftingTableHigh;
import helporme.armsforge.common.tiles.TileEntityArmssmithTable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmssmithTable extends BlockCraftingTableHigh
{
    public BlockArmssmithTable()
    {
        super(Material.iron, "ArmssmithTable");
        setHardness(10f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeMetal);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityArmssmithTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityArmssmithTable();
    }
}
