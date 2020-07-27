package helporme.armsforge.common.block.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.block.BlockMasterAnvil;
import net.minecraft.block.Block;

import java.util.HashMap;

public class BlocksRegistry
{
    private static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void registerDefaultBlocks()
    {
        registerBlock(new BlockMasterAnvil());
    }

    public static void registerBlock(Block block)
    {
        String name = block.getClass().getSimpleName();
        if (block instanceof INamedBlock)
        {
            INamedBlock namedBlock = (INamedBlock)block;
            name = namedBlock.getName();
        }
        GameRegistry.registerBlock(block, name);
        blocks.put(name, block);
    }

    public static boolean isContainsBlockWithName(String name)
    {
        return blocks.containsKey(name);
    }

    public static Block getBlockByName(String name)
    {
        return blocks.get(name);
    }

    public static Iterable<Block> getAllBlocks()
    {
        return blocks.values();
    }
}
