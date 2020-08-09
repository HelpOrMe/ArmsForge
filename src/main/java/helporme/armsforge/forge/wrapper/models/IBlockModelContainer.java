package helporme.armsforge.forge.wrapper.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public interface IBlockModelContainer extends IItemModelContainer, IModelInfoContainer
{
    Block getBlock();

    Class<? extends TileEntity> getTileClass();

    @SideOnly(Side.CLIENT)
    TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo);
}
