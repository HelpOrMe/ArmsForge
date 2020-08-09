package helporme.armsforge.forge.wrapper.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlock extends ItemBlockWithMetadata
{
    public ItemBlock(Block block)
    {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "_" + stack.getItemDamage();
    }
}
