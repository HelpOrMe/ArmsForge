package helporme.armsforge.common.blocks.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public interface IModelContainer
{
    Block getBlock();

    Class<? extends TileEntity> getTileClass();

    @SideOnly(Side.CLIENT)
    TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo);

    @SideOnly(Side.CLIENT)
    IItemRenderer getItemRenderer(ModelInfo modelInfo);

    @SideOnly(Side.CLIENT)
    ModelInfo getModelInfo();
}
