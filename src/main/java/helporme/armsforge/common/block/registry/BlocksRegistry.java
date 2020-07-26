package helporme.armsforge.common.block.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.block.BlockMasterAnvil;
import net.minecraft.block.Block;

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
