package helporme.armsforge.common.registry.blocks;

import helporme.armsforge.common.registry.RegistryList;
import helporme.armsforge.common.registry.utils.INamed;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public abstract class BlocksList extends RegistryList
{
    protected final HashMap<String, Block> blocks = new HashMap<>();

    public void addBlocks(Block... blocks)
    {
        for (Block block : blocks)
        {
            addBlock(block);
        }
    }

    public void addBlock(Block block)
    {
        String name = block.getClass().getSimpleName();
        if (block instanceof INamed)
        {
            INamed namedBlock = (INamed)block;
            name = namedBlock.getName();
        }
        blocks.put(name, block);
    }

    public Block getBlockByName(String name)
    {
        return blocks.get(name);
    }

    public Map<String, Block> getBlocks()
    {
        return new HashMap<>(blocks);
    }
}
