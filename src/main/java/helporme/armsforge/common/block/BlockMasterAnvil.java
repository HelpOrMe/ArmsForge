package helporme.armsforge.common.block;

import helporme.armsforge.common.block.tiles.TileEntityMasterAnvil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvil extends MasterAnvilBase
{
    public BlockMasterAnvil()
    {
        super("masterAnvil");
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(15f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
     }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvil();
    }
}
