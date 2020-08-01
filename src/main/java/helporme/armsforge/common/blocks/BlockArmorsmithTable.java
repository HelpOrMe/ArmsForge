package helporme.armsforge.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.TileEntityTableUpRenderer;
import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityArmorsmithTable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorsmithTable extends BlockTableBase
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

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityTableUpRenderer(modelInfo);
    }
}
