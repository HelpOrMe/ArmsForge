package helporme.armsforge.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.forge.wrapper.blocks.BlockWithMetaBase;
import helporme.armsforge.forge.wrapper.items.ItemBlock;
import helporme.armsforge.common.registry.blocks.BlocksList;
import helporme.armsforge.common.registry.blocks.FunctionalBlocksList;
import helporme.armsforge.common.registry.blocks.ResourceBlocksList;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlocksRegistry
{
    private static final BlocksList[] blocksLists = new BlocksList[]
            {
                    new ResourceBlocksList(),
                    new FunctionalBlocksList()
            };


    public static void createDefaultBlocks()
    {
        for (BlocksList blocksList : blocksLists)
        {
            blocksList.addDefault();
        }
    }

    public static void registerBlocks()
    {
        for (BlocksList blocksList : blocksLists)
        {
            registerBlocksFrom(blocksList.getBlocks());
        }
    }

    public static void registerBlocksFrom(Map<String, Block> blocks)
    {
        for (String name : blocks.keySet())
        {
            Block block = getBlockByName(name);
            if (block instanceof BlockWithMetaBase)
            {
                registerBlockWithItem(block, name);
            }
            else
            {
                registerBlock(block, name);
            }
        }
    }

    public static void registerBlockWithItem(Block block, String name)
    {
        GameRegistry.registerBlock(block, ItemBlock.class, name);
    }

    public static void registerBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, name);
    }

    public static Block getBlockByName(String name)
    {
        for (BlocksList blocksList : blocksLists)
        {
            Map<String, Block> blocks = blocksList.getBlocks();
            if (blocks.containsKey(name))
            {
                return blocks.get(name);
            }
        }
        throw new IllegalArgumentException("Can't get a block with name: " + name);
    }

    public static Iterable<Block> getAllBlocks()
    {
        List<Block> allBlocks = new ArrayList<>();
        for (BlocksList blocksList : blocksLists)
        {
            allBlocks.addAll(blocksList.getBlocks().values());
        }
        return allBlocks;
    }
}
