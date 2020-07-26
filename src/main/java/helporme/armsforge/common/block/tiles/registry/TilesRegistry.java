package helporme.armsforge.common.block.tiles.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.block.model.ModelSuite;
import helporme.armsforge.common.block.model.registry.ModelRegistry;
import net.minecraft.tileentity.TileEntity;

public class TilesRegistry
{
    public static void registerTilesFromModelSuites()
    {
        for (ModelSuite modelSuite : ModelRegistry.getAllModelSuites())
        {
            registerTile(modelSuite.tileClass);
        }
    }

    public static void registerTile(Class<? extends TileEntity> tileClass)
    {
        GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
    }
}
