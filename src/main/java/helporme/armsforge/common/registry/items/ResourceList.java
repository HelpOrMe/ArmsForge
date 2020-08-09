package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.base.ItemResource;
import helporme.armsforge.common.items.base.ItemResourceColored;
import helporme.armsforge.common.registry.utils.MaterialColors;

public class ResourceList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new ItemResourceColored("ChainCanvasBig", MaterialColors.chainColors),
                new ItemResourceColored("ChainCanvasMedium", MaterialColors.chainColors),
                new ItemResourceColored("ChainCanvasSmall", MaterialColors.chainColors),
                new ItemResourceColored("HandfulRings", MaterialColors.chainColors),
                new ItemResourceColored("HandfulRivets", MaterialColors.rivetColors),
                new ItemResourceColored("MetalIngot", MaterialColors.metalColors),
                new ItemResourceColored("Ring", MaterialColors.chainColors),
                new ItemResourceColored("Rivet", MaterialColors.rivetColors),
                new ItemResourceColored("Wire", MaterialColors.wireColors),
                new ItemResourceColored("MetalPlate", MaterialColors.plateColors),

                new ItemResource("CommonCloth"),
                new ItemResource("CottonWool"),
                new ItemResource("PackingStuds"),
                new ItemResource("Stud"),
                new ItemResource("TanningSolution")
        );
    }
}
