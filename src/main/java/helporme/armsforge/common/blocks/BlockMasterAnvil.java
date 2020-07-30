package helporme.armsforge.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityCrafingTableRendererBase;
import helporme.armsforge.common.blocks.base.BlockCraftingTableBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityMasterAnvil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMasterAnvil extends BlockCraftingTableBase
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
        return TileEntityMasterAnvil.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityMasterAnvil();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityCrafingTableRendererBase(modelInfo);
    }
}
