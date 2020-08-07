package helporme.armsforge.forge.wrapper.utils;

import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.registry.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public final class ItemStackHelper
{
    public static ItemStack getItemStack(String itemName)
    {
        return getItemStack(itemName, 0);
    }

    public static ItemStack getItemStack(String itemName, int meta)
    {
        return getItemStack(itemName, 1, meta);
    }

    public static ItemStack getItemStack(String itemName, int count, int meta)
    {
        return new ItemStack((Item)Item.itemRegistry.getObject(itemName), count, meta);
    }

    public static ItemStack[] repeatItemStack(ItemStack stack, int times)
    {
        Set<ItemStack> itemStackSet = new HashSet<>();
        for (int i = 0; i < times; i++)
        {
            itemStackSet.add(stack);
        }
        return itemStackSet.toArray(new ItemStack[0]);
    }

    public static String convertItemStackToString(ItemStack itemStack)
    {
        return Item.itemRegistry.getNameForObject(itemStack.getItem()) + ":" + itemStack.getItemDamage();
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
