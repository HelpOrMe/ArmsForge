package helporme.armsforge.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import helporme.armsforge.api.items.hammer.DefaultHammerTypes;
import helporme.armsforge.client.render.colors.Colors;
import helporme.armsforge.common.items.base.ItemBase;
import helporme.armsforge.common.items.base.ItemColoredBase;
import helporme.armsforge.common.items.base.ItemHammerBase;
import helporme.armsforge.common.registry.interfaces.INamed;
import net.minecraft.item.Item;

import java.util.HashMap;

public final class ItemsRegistry
{
    private static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void createDefaultItems()
    {
        AddResources();
        AddTools();
    }

    //TODO: ItemsList's
    public static void AddResources()
    {
        addItems(
                new ItemColoredBase("ChainCanvasBig", Colors.chainColors),
                new ItemColoredBase("ChainCanvasMedium", Colors.chainColors),
                new ItemColoredBase("ChainCanvasSmall", Colors.chainColors),
                new ItemColoredBase("HandfulRings", Colors.chainColors),
                new ItemColoredBase("HandfulRivets", Colors.rivetColors),
                new ItemColoredBase("MetalIngot", Colors.metalColors),
                new ItemColoredBase("Ring", Colors.chainColors),
                new ItemColoredBase("Rivet", Colors.rivetColors),
                new ItemColoredBase("Wire", Colors.wireColors),
                new ItemColoredBase("MetalPlate", Colors.plateColors),

                new ItemBase("CommonCloth"),
                new ItemBase("CottonWool"),
                new ItemBase("PackingStuds"),
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
