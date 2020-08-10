package helporme.armsforge.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.registry.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class ItemsRegistry
{
    private static final ItemList[] itemLists = new ItemList[]
            {
                    new ResourceList(),
                    new HammerList(),
                    new WeaponList(),
                    new ArmorList(),
                    new MiscList()
            };

    public static void createDefaultItems()
    {
        for (ItemList itemList : itemLists)
        {
            itemList.addDefault();
        }
    }

    public static void registerItems()
    {
        for (ItemList itemList : itemLists)
        {
            registerItemsFrom(itemList.getItems());
        }
    }

    public static void registerItemsFrom(Map<String, Item> items)
    {
        for (String name : items.keySet())
        {
            GameRegistry.registerItem(items.get(name), name);
        }
    }

    public static Item getItemByName(String name)
    {
        for (ItemList itemList : itemLists)
        {
            Map<String, Item> items = itemList.getItems();
            if (items.containsKey(name))
            {
                return items.get(name);
            }
        }
        throw new IllegalArgumentException("Can't get an item with name: " + name);
    }

    public static Collection<Item> getAllItems()
    {
        List<Item> allItems = new ArrayList<>();
        for (ItemList itemList : itemLists)
        {
            allItems.addAll(itemList.getItems().values());
        }
        return allItems;
    }
}
