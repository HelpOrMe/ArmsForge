package helporme.armsforge.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.TileEntitySupportTableRenderer;
import helporme.armsforge.common.blocks.base.BlockTableBase;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntitySupportTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSupportTable extends BlockTableBase
{
    public BlockSupportTable()
    {
        super(Material.wood, "SupportTable");
        setHardness(5f);
        setResistance(10f);
        setHarvestLevel("axe", 1);
        setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntitySupportTableRenderer(modelInfo);
    }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntitySupportTable.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntitySupportTable();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, player, itemStack);
        updateShelfStatus(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        TileEntitySupportTable supportTable = (TileEntitySupportTable)world.getTileEntity(x, y, z);
        Block blockUnderTable = world.getBlock(x, y - 1, z);
        updateShelfStatus(world, x, y, z);
        if (supportTable.isShelf && blockUnderTable == Blocks.air)
        {
            // Destroy block and drop item
            world.func_147480_a(x, y, z, true);
        }
    }

    public void updateShelfStatus(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            TileEntitySupportTable supportTable = (TileEntitySupportTable)world.getTileEntity(x, y, z);
            supportTable.updateShelfStatus();
            supportTable.rotateIfHaveTableUnder();
        }
    }
}
