package helporme.armsforge.common.blocks.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
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
}
