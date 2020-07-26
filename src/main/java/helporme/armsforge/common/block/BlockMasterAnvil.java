package helporme.armsforge.common.block;

import helporme.armsforge.common.block.base.MasterAnvilBase;
import helporme.armsforge.common.block.tiles.TileEntityMasterAnvil;
import helporme.armsforge.common.core.Version;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvil extends MasterAnvilBase
{
    public BlockMasterAnvil()
    {
        super("MasterAnvil");
        setCreativeTab(CreativeTabs.tabBlock);
        setHardness(15f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setBlockTextureName(Version.modid + ":" + name);
        setBlockBounds(
                0.11f, 0f, 0.11f,
                0.89f, 1f, 0.89f);
     }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvil();
    }
}
