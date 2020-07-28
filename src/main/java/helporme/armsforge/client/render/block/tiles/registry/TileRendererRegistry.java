package helporme.armsforge.client.render.block.tiles.registry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.blocks.models.ModelSuite;
import helporme.armsforge.common.blocks.models.registry.ModelsRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileRendererRegistry
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
