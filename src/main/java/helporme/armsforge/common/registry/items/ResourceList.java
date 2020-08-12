package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.registry.utils.MaterialColors;
import helporme.armsforge.forge.wrapper.items.ItemColored;
import helporme.armsforge.forge.wrapper.items.base.ItemBase;

public class ResourceList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                getColoredResource("ChainCanvasBig", MaterialColors.chainColors),
                getColoredResource("ChainCanvasMedium", MaterialColors.chainColors),
                getColoredResource("ChainCanvasSmall", MaterialColors.chainColors),
                getColoredResource("HandfulRings", MaterialColors.chainColors),
                getColoredResource("HandfulRivets", MaterialColors.rivetColors),
                getColoredResource("MetalIngot", MaterialColors.metalColors),
                getColoredResource("Ring", MaterialColors.chainColors),
                getColoredResource("Rivet", MaterialColors.rivetColors),
                getColoredResource("Wire", MaterialColors.wireColors),
                getColoredResource("MetalPlate", MaterialColors.plateColors));

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
                "MagicPlate", "HeavenCrystal");
    }

    public ItemColored getColoredResource(String name, int[] colors)
    {
        return new ItemColored(name, "resources", colors);
    }

    public void addResources(String... resourceNames)
    {
        ItemBase[] resourceItems = new ItemBase[resourceNames.length];
        for (int i = 0; i < resourceItems.length; i++)
        {
            resourceItems[i] = new ItemBase(resourceNames[i], "resources");
        }
        addItems(resourceItems);
    }
}
