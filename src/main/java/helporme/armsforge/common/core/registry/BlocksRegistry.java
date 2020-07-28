package helporme.armsforge.common.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.blocks.*;
import helporme.armsforge.common.blocks.base.BlockWithMetaBase;
import helporme.armsforge.common.core.registry.interfaces.INamed;
import helporme.armsforge.common.items.base.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.HashMap;

public class BlocksRegistry
{
    private static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void createDefaultBlocks()
    {
        addBlocks(
                new BlockMasterAnvil(),
                new BlockMasterAnvilGold(),
                new BlockArmorerTable(),
                new BlockArmorsmithTable(),
                new BlockSupportTable(),
                new MetalBlock()
        );
    }

    public static void addBlocks(Block... blocks)
    {
        for (Block block : blocks)
        {
            addBlock(block);
        }
    }

    public static void addBlock(Block block)
    {
        String name = block.getClass().getSimpleName();
        if (block instanceof INamed)
        {
            INamed namedBlock = (INamed)block;
            name = namedBlock.getName();
        }
        blocks.put(name, block);
    }

    public static void registerBlocks()
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
        GameRegistry.registerBlock(block, ItemBlockBase.class, name);
    }

    public static void registerBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, name);
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
