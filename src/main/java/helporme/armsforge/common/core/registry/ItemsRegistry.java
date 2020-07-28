package helporme.armsforge.common.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.api.items.hammer.DefaultHammerTypes;
import helporme.armsforge.common.core.registry.interfaces.INamed;
import helporme.armsforge.common.items.base.ItemBase;
import helporme.armsforge.common.items.base.ItemHammerBase;
import helporme.armsforge.common.items.base.ItemWithSubTypesBase;
import net.minecraft.item.Item;
import thaumcraft.common.items.armor.ItemFortressArmor;

import java.util.HashMap;

public final class ItemsRegistry
{
    private static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void createDefaultItems()
    {
        AddResources();
        AddTools();
    }

    public static void AddResources()
    {
        addItems(
                new ItemWithSubTypesBase("ChainCanvasBig", 10),
                new ItemWithSubTypesBase("ChainCanvasMedium", 10),
                new ItemWithSubTypesBase("ChainCanvasSmall", 10),
                new ItemWithSubTypesBase("HandfulRings", 10),
                new ItemWithSubTypesBase("HandfulRivets", 10),
                new ItemWithSubTypesBase("IngotBasic", 9),
                new ItemWithSubTypesBase("Ring", 10),
                new ItemWithSubTypesBase("Rivet", 10),
                new ItemWithSubTypesBase("Wire", 10),
                new ItemBase("CommonCloth"),
                new ItemBase("CottonWool"),
                new ItemBase("PackingStuds"),
                new ItemBase("SteelPlate"),
                new ItemBase("Stud"),
                new ItemBase("TanningSolution")
        );
    }

    public static void AddTools()
    {
        addItems(
                new ItemHammerBase("SmallhammerIron", DefaultHammerTypes.smallIron),
                new ItemHammerBase("SmallhammerSteel", DefaultHammerTypes.smallSteel),
                new ItemHammerBase("SmallhammerThaum", DefaultHammerTypes.smallThaum),

                new ItemHammerBase("MediumhammerIron", DefaultHammerTypes.mediumIron),
                new ItemHammerBase("MediumhammerSteel", DefaultHammerTypes.mediumSteel),
                new ItemHammerBase("MediumhammerThaum", DefaultHammerTypes.mediumThaum),

                new ItemHammerBase("BighammerIron", DefaultHammerTypes.bigIron),
                new ItemHammerBase("BighammerSteel", DefaultHammerTypes.bigSteel),
                new ItemHammerBase("BighammerThaum", DefaultHammerTypes.bigThaum)
        );
    }

    public static void addItems(Item... items)
    {
        for (Item item : items)
        {
            addItem(item);
        }
    }

    public static void addItem(Item item)
    {
        String name = item.getClass().getSimpleName();
        if (item instanceof INamed)
        {
            INamed namedItem = (INamed)item;
            name = namedItem.getName();
        }
        items.put(name, item);
    }

    public static void registerItems()
    {
        for (String name : items.keySet())
        {
            GameRegistry.registerItem(getItemByName(name), name);
        }
    }

    public static Item getItemByName(String name)
    {
        return items.get(name);
    }

    public static Iterable<Item> getAllItems()
    {
        return items.values();
    }
}
