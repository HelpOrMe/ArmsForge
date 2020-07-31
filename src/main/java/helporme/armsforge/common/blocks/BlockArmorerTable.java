package helporme.armsforge.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityTableUpRendererBase;
import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityArmorerTable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorerTable extends BlockTableBase
{
    public BlockArmorerTable()
    {
        super(Material.iron, "ArmorerTable");
        setHardness(10f);
        setResistance(10f);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeMetal);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityArmorerTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityArmorerTable();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityTableUpRendererBase(modelInfo);
    }
}
