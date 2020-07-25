package helporme.armsforge.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import org.lwjgl.Sys;

public class BlocksRegistry
{
    public static BlockMasterAnvil masterAnvil = new BlockMasterAnvil();

    public static void RegisterDefaultBlocks()
    {
        RegisterBlock(masterAnvil);
    }

    private static void RegisterBlock(Block block)
    {
        String name = block.getClass().getSimpleName();
        if (block instanceof INamedBlock)
        {
            INamedBlock namedBlock = (INamedBlock)block;
            name = namedBlock.getName();
        }
        GameRegistry.registerBlock(block, name);
    }
}
