package helporme.armsforge.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.common.registry.items.HammersList;
import helporme.armsforge.common.registry.items.ItemsList;
import helporme.armsforge.common.registry.items.ResourcesList;
import helporme.armsforge.common.registry.items.MiscList;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class ItemsRegistry
{
    private static final ItemsList[] itemsLists = new ItemsList[]
            {
                    new ResourcesList(),
                    new HammersList(),
                    new MiscList()
            };

    public static void createDefaultItems()
    {
        for (ItemsList itemsList : itemsLists)
        {
            itemsList.addDefault();
        }
    }

    public static void registerItems()
    {
        for (ItemsList itemsList : itemsLists)
        {
            registerItemsFrom(itemsList.getItems());
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
        for (ItemsList itemsList : itemsLists)
        {
            Map<String, Item> items = itemsList.getItems();
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
        for (ItemsList itemsList : itemsLists)
        {
            allItems.addAll(itemsList.getItems().values());
        }
        return allItems;
    }
}
