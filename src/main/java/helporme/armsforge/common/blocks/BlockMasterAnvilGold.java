package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.MasterAnvilBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.blocks.tiles.TileEntityMasterAnvil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvilGold extends MasterAnvilBase
{
    public BlockMasterAnvilGold()
    {
        super("MasterAnvilGold");
        setHardness(12.5f);
        setResistance(12.5f);
        setHarvestLevel("pickaxe", 2);
        setBlockBounds(0.11f, 0f, 0.11f, 0.89f, 1f, 0.89f);
     }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityMasterAnvil.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvil();
    }

    @Override
    public ModelInfo getModelInfo()
    {
        return new ModelInfo("blocks/MasterAnvilGold.png", "MasterAnvil.obj");
    }
}
