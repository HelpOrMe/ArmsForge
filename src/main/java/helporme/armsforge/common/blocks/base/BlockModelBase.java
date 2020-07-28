package helporme.armsforge.common.blocks.base;

import helporme.armsforge.client.render.block.tiles.base.TileEntityRenderEightFacedBase;
import helporme.armsforge.client.render.item.base.ItemRendererBase;
import helporme.armsforge.common.blocks.models.IModelContainer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.blocks.models.ModelSuite;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockModelBase extends BlockContainerBase implements IModelContainer
{
    public BlockModelBase(Material material, String name)
    {
        super(material, name);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        int dir = MathHelper.floor_double((player.rotationYaw * 8f) / 360F + 0.5d);
        world.setBlockMetadataWithNotify(x, y, z, dir, 3);
        createNewTileEntity(world, world.getBlockMetadata(x, y, z));
    }

    public ModelInfo getModelInfo()
    {
        return new ModelInfo(name);
    }

    public ModelSuite getModelSuite()
    {
        ModelInfo modelInfo = getModelInfo();
        return new ModelSuite(this, getTileClass(),
                new TileEntityRenderEightFacedBase(modelInfo), new ItemRendererBase(modelInfo), modelInfo);
    }
}