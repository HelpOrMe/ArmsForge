package helporme.armsforge.common.blocks;

import helporme.armsforge.common.blocks.base.BlockCraftingTableBase;
import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.common.tiles.TileEntityMasterAnvil;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvil extends BlockCraftingTableBase
{
    public BlockMasterAnvil()
    {
        super(Material.anvil,"MasterAnvil");
        setStepSound(soundTypeAnvil);
        setHardness(10f);
        setResistance(10f);
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
}
