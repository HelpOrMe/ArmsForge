package helporme.armsforge.client.render.block.tiles.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.block.model.ModelSuite;
import helporme.armsforge.common.block.model.registry.ModelRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileRendererRegistry
{
    public static void registerTileRenderersFromModelSuites()
    {
        for (ModelSuite modelSuite : ModelRegistry.getAllModelSuites())
        {
            registerTileRenderer(modelSuite.tileClass, modelSuite.tileRenderer);
        }
    }

    public static void registerTileRenderer(Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer renderer)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tileClass, renderer);
    }
}
