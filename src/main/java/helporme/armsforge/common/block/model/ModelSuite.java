package helporme.armsforge.common.block.model;

import helporme.armsforge.client.render.block.tiles.base.TileEntityRendererBase;
import helporme.armsforge.client.render.item.base.ItemRendererBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class ModelSuite
{
    public Block block;
    public Class<? extends TileEntity> tileClass;
    public TileEntitySpecialRenderer tileRenderer;
    public IItemRenderer itemRenderer;
    public ModelInfo modelInfo;

    public ModelSuite(Block block, Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer tileRenderer,
                      IItemRenderer itemRenderer, ModelInfo modelInfo)
    {
        this.block = block;
        this.tileClass = tileClass;
        this.tileRenderer = tileRenderer;
        this.itemRenderer = itemRenderer;
        this.modelInfo = modelInfo;
    }

    public ModelSuite(Block block, ModelInfo modelInfo)
    {
        this(block, TileEntity.class, new TileEntityRendererBase(modelInfo),
                new ItemRendererBase(modelInfo), modelInfo);
    }
}
