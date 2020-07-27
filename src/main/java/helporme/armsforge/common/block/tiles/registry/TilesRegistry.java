package helporme.armsforge.common.block.tiles.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class TilesRegistry
{
    public static void registerDefaultTiles()
    {

    }

    public static void registerTile(Class<? extends TileEntity> tileClass)
    {
        GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
    }
}
