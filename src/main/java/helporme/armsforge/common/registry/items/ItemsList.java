package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.registry.RegistryList;
import helporme.armsforge.common.registry.utils.INamed;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public abstract class ItemsList extends RegistryList
{
    protected HashMap<String, Item> items = new HashMap<String, Item>();

    public void addItems(Item... items)
    {
        for (Item item : items)
        {
            addItem(item);
        }
    }

    public void addItem(Item item)
    {
        String name = item.getClass().getSimpleName();
        if (item instanceof INamed)
        {
            INamed namedItem = (INamed)item;
            name = namedItem.getName();
        }
        items.put(name, item);
    }

    public Item getItemByName(String name)
    {
        return items.get(name);
    }

    public Map<String, Item> getItems()
    {
        return new HashMap<String, Item>(items);
    }
}
