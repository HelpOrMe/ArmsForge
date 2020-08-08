package helporme.armsforge.forge.wrapper.utils;

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

    public static String convertItemToString(ItemStack itemStack)
    {
        return Item.itemRegistry.getNameForObject(itemStack.getItem()) + "@" + itemStack.getItemDamage();
    }

    public static ItemStack convertStringToItem(String convertedItemStack)
    {
        String[] nameAndMeta = convertedItemStack.split("@");
        String name = nameAndMeta[0];
        int meta = Integer.parseInt(nameAndMeta[1]);
        return new ItemStack((Item)Item.itemRegistry.getObject(name), 1, meta);
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
