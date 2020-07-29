package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.MasterAnvilBase;
import helporme.armsforge.common.tiles.TileEntityMasterAnvilGold;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvil extends MasterAnvilBase
{
    public BlockMasterAnvil()
    {
        super("MasterAnvil");
        setHardness(10f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setBlockBounds(0.11f, 0f, 0.11f, 0.89f, 1f, 0.89f);
     }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityMasterAnvilGold.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvilGold();
    }
}
