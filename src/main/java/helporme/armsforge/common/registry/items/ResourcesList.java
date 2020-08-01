package helporme.armsforge.common.registry.items;

import helporme.armsforge.forge.wrapper.items.ItemBase;
import helporme.armsforge.forge.wrapper.items.ItemColoredBase;
import helporme.armsforge.common.registry.utils.Colors;

public class ResourcesList extends ItemsList
{
    @Override
    public void createDefault()
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
}
