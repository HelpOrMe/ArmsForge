package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.common.tiles.TileEntityMasterAnvilAdvanced;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvilAdvanced extends BlockTableBase
{
    public BlockMasterAnvilAdvanced()
    {
        super(Material.anvil,"MasterAnvilAdvanced");
        setHardness(12.5f);
        setResistance(12.5f);
        setHarvestLevel("pickaxe", 2);
        setBlockBounds(0.11f, 0f, 0.11f, 0.89f, 1f, 0.89f);
     }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityMasterAnvilAdvanced.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvilAdvanced();
    }
}