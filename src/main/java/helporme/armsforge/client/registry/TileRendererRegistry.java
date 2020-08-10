package helporme.armsforge.client.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import helporme.armsforge.forge.wrapper.render.models.ModelBlockSuite;
import helporme.armsforge.common.registry.BlockModelsRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public final class TileRendererRegistry
{
    public static void registerFromBlocks()
    {
        for (ModelBlockSuite modelSuite : BlockModelsRegistry.getAllModelSuites())
        {
            register(modelSuite.tileClass, modelSuite.tileRenderer);
        }
    }

    public static void register(Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer renderer)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tileClass, renderer);
    }
}
