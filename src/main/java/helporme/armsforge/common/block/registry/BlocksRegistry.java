package helporme.armsforge.common.block.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.block.BlockArmorerTable;
import helporme.armsforge.common.block.BlockArmorsmithTable;
import helporme.armsforge.common.block.BlockMasterAnvil;
import helporme.armsforge.common.block.BlockSupportTable;
import net.minecraft.block.Block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BlocksRegistry
{
    private static HashSet<Block> unregisteredBlocks = new HashSet<Block>();
    private static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void createDefaultBlocks()
    {
        addToUnregisteredBlocks(
                new BlockMasterAnvil(),
                new BlockArmorerTable(),
                new BlockArmorsmithTable(),
                new BlockSupportTable());
    }

    public static void addToUnregisteredBlocks(Block... blocks)
    {
        unregisteredBlocks.addAll(Arrays.asList(blocks));
    }

    public static void registerBlocks()
    {
        for (Block block : unregisteredBlocks)
        {
            registerBlock(block);
        }
        unregisteredBlocks.clear();
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

    public static Block getBlockByName(String name)
    {
        return blocks.get(name);
    }

    public static Iterable<Block> getAllBlocks()
    {
        return blocks.values();
    }
}
