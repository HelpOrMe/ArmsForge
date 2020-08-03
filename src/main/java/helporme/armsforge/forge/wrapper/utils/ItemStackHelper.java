package helporme.armsforge.forge.wrapper.utils;

import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.registry.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public final class ItemStackHelper
{
    public static ItemStack getOwnBlockStack(String blockName)
    {
        return getOwnBlockStack(blockName, 0);
    }

    public static ItemStack getOwnBlockStack(String blockName, int meta)
    {
        return getOwnBlockStack(blockName, 1, meta);
    }

    public static ItemStack getOwnBlockStack(String blockName, int count, int meta)
    {
        return getBlockStack(Version.modid + ":" + blockName, count, meta);
    }

    public static ItemStack getBlockStack(String blockName)
    {
        return getBlockStack(blockName, 0);
    }

    public static ItemStack getBlockStack(String blockName, int meta)
    {
        return getBlockStack(blockName, 1, meta);
    }

    public static ItemStack getBlockStack(String blockName, int count, int meta)
    {
        return new ItemStack(Block.getBlockFromName(blockName), count, meta);
    }

    public static ItemStack getOwnItemStack(String itemName)
    {
        return getOwnItemStack(itemName, 0);
    }

    public static ItemStack getOwnItemStack(String itemName, int meta)
    {
        return getOwnItemStack(itemName, 1, meta);
    }

    public static ItemStack getOwnItemStack(String itemName, int count, int meta)
    {
        return new ItemStack(ItemsRegistry.getItemByName(itemName), count, meta);
    }

    public static Object[] repeatStack(ItemStack stack, int times)
    {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < times; i++)
        {
            itemStacks.add(stack);
        }
        return itemStacks.toArray();
    }

    public static ItemStack clone(ItemStack stack)
    {
        return clone(stack, stack.stackSize, stack.getItemDamage());
    }

    public static ItemStack clone(ItemStack stack, int newSize)
    {
        return new ItemStack(stack.getItem(), newSize, stack.getItemDamage());
    }

    public static ItemStack clone(ItemStack stack, int newSize, int newMeta)
    {
        return new ItemStack(stack.getItem(), newSize, newMeta);
    }
}
