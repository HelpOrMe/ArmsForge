package helporme.armsforge.common.block.tiles;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class TilesRegistry
{
    public static void RegisterDefaultTiles()
    {
        RegisterTile(TileEntityMasterAnvil.class);
    }

    private static void RegisterTile(Class<? extends TileEntity> tileClass)
    {
        GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
    }
}
