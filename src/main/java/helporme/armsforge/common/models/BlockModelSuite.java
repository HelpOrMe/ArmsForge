package helporme.armsforge.common.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.models.ModelInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class BlockModelSuite
{
    public final Block block;
    public final Class<? extends TileEntity> tileClass;
    public final TileEntitySpecialRenderer tileRenderer;
    public final IItemRenderer itemRenderer;
    public final ModelInfo modelInfo;

    public BlockModelSuite(Block block, Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer tileRenderer,
                           IItemRenderer itemRenderer, ModelInfo modelInfo)
    {
        this.block = block;
        this.tileClass = tileClass;
        this.tileRenderer = tileRenderer;
        this.itemRenderer = itemRenderer;
        this.modelInfo = modelInfo;
    }
}
