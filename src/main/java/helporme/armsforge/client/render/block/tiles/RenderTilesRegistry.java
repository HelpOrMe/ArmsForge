package helporme.armsforge.client.render.block.tiles;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.block.tiles.TileEntityMasterAnvil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class RenderTilesRegistry
{
    public static void RegisterDefaultTileRenderers()
    {
        RegisterTileRenderer(TileEntityMasterAnvil.class, new RenderTileEntityMasterAnvil());
    }

    private static void RegisterTileRenderer(Class<? extends TileEntity> tileClass, TileEntitySpecialRenderer renderer)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tileClass, renderer);
    }
}
