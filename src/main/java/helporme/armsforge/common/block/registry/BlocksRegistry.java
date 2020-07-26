package helporme.armsforge.common.block.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.block.model.ModelSuite;
import helporme.armsforge.common.block.model.registry.ModelRegistry;
import net.minecraft.block.Block;

import java.util.HashMap;

public class BlocksRegistry
{
    private static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void registerBlocksFromModelSuites()
    {
        for (ModelSuite modelSuite : ModelRegistry.modelSuites)
        {
            registerBlock(modelSuite.block);
        }
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
}
