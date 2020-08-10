package helporme.armsforge.forge.wrapper.render.models;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class ModelBlockSuite
{
    public final Block block;
    public final Class<? extends TileEntity> tileClass;
    public final TileEntitySpecialRenderer tileRenderer;
    public final IItemRenderer itemRenderer;
    public final ModelInfo modelInfo;

    public ModelBlockSuite(Block block, Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer tileRenderer,
                           IItemRenderer itemRenderer, ModelInfo modelInfo)
    {
        this.block = block;
        this.tileClass = tileClass;
        this.tileRenderer = tileRenderer;
        this.itemRenderer = itemRenderer;
        this.modelInfo = modelInfo;
    }
}
