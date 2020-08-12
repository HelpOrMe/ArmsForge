package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.registry.utils.MaterialColors;
import helporme.armsforge.forge.wrapper.items.base.ItemBase;

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
        );

        addResources(
                "CommonCloth", "CottonWool",  "PackingStuds", "Stud",
                "TanningSolution", "BurnPlate",  "BrigantPlate",
                "BlessedPlate", "AngelFeather", "Clasp", "CursedPlate",
                "CrimsonFabric", "DevilScales", "DragonLeather",
                "Edem", "ElongatedPlate", "EnderCrystal",
                "GodPlate", "Hell", "HellLether", "HellShards",
                "ImpSkin", "KozanePlates", "Laces", "LamellarPlates",
                "LaminarPlates", "LeatherPlate", "MagicLaces",
                "MetalScales", "PaperPlate", "PressedPaper",
                "PrimalFabric", "ProcessedBone", "QuiltedFabric",
                "QuiltedMagicFabric", "RunePaper", "RougeMagicFabric",
                "RuneSkin", "SkinStripes", "SpatialFabric",
                "Shadow", "PaperStack", "SpatialKozanePlates",
                "StitchedFabric", "SuperdensePlate", "TannedLeather",
                "UnicornLether", "MagicRivet", "StrongLaces",
                "MagicPlate", "HeavenCrystal"
        );
    }

    public void getColoredResource(String name, int[] colors)
    {
        return ItemColored(name, colors, )
    }

    public void addResources(String... resourceNames)
    {
        ItemBase[] resourceItems = new ItemBase[resourceNames.length];
        for (int i = 0; i < resourceItems.length; i++)
        {
            resourceItems[i] = new ItemBase(resourceNames[i], "items/resources/" + resourceNames[i]);
        }
        addItems(resourceItems);
    }
}
