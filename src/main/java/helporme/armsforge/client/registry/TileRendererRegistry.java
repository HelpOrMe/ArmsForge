package helporme.armsforge.client.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import helporme.armsforge.common.blocks.models.ModelSuite;
import helporme.armsforge.common.registry.ModelsRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public final class TileRendererRegistry
{
    public static void registerTileRenderersFromModelSuites()
    {
        for (ModelSuite modelSuite : ModelsRegistry.getAllModelSuites())
        {
            registerTileRenderer(modelSuite.tileClass, modelSuite.tileRenderer);
        }
    }

    public static void registerTileRenderer(Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer renderer)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tileClass, renderer);
    }
}
