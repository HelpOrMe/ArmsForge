package helporme.armsforge.common.block.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.block.IMasterAnvil;
import helporme.armsforge.client.render.block.tiles.base.TileEntityRenderEightFacedBase;
import helporme.armsforge.common.block.model.IModel;
import helporme.armsforge.common.block.model.ModelInfo;
import helporme.armsforge.common.block.model.ModelSuite;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class MasterAnvilBase extends BlockContainerBase implements IMasterAnvil, IModel
{
    public MasterAnvilBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeAnvil);
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

    @SideOnly(Side.CLIENT)
    public ModelSuite getModelSuite()
    {
        ModelSuite modelSuite = new ModelSuite(this, new ModelInfo(name));
        modelSuite.tileRenderer = new TileEntityRenderEightFacedBase(modelSuite.modelInfo);
        return modelSuite;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        int dir = MathHelper.floor_double((player.rotationYaw * 8f) / 360F + 0.5d);
        world.setBlockMetadataWithNotify(x, y, z, dir, 3);
        createNewTileEntity(world, world.getBlockMetadata(x, y, z));
    }
}
