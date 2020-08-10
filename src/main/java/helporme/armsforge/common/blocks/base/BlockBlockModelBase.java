package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.forge.wrapper.render.models.IBlockModelContainer;
import helporme.armsforge.forge.wrapper.blocks.BlockContainerBase;
import helporme.armsforge.forge.wrapper.render.items.ItemRendererBase;
import helporme.armsforge.forge.wrapper.render.blocks.TileEntityFacedRenderer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public abstract class BlockBlockModelBase extends BlockContainerBase implements IBlockModelContainer
{
    public BlockBlockModelBase(Material material, String name)
    {
        super(material, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        int dir = MathHelper.floor_double((player.rotationYaw * 4f) / 360F + 0.5d);
        world.setBlockMetadataWithNotify(x, y, z, dir, 3);
        createNewTileEntity(world, world.getBlockMetadata(x, y, z));
    }

    @Override
    public Block getBlock()
    {
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityFacedRenderer(modelInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer(ModelInfo modelInfo)
    {
        return new ItemRendererBase(modelInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelInfo getModelInfo()
    {
        return new ModelInfo("blocks/" + name + ".png", "blocks/" + name + ".obj");
    }
}
