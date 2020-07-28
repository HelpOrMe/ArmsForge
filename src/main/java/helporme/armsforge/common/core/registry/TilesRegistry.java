package helporme.armsforge.common.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.blocks.base.BlockContainerBase;
import helporme.armsforge.common.core.registry.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public final class TilesRegistry
{
    public static void registerTilesFromBlocks()
    {
        for (Block block : BlocksRegistry.getAllBlocks())
        {
            if (block instanceof BlockContainerBase)
            {
                BlockContainerBase blockContainer = (BlockContainerBase)block;
                registerTile(blockContainer.getTileClass());
            }
        }
    }

    public static void registerTile(Class<? extends TileEntity> tileClass)
    {
        GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
    }
}
