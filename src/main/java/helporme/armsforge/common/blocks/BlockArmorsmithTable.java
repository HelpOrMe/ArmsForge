package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockCraftingTableHigh;
import helporme.armsforge.common.tiles.TileEntityArmorsmithTable;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorsmithTable extends BlockCraftingTableHigh
{
    public BlockArmorsmithTable()
    {
        super(Material.iron, "ArmorsmithTable");
        setHardness(10f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeMetal);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityArmorsmithTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityArmorsmithTable();
    }
}
