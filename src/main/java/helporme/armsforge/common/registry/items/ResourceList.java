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
                new ItemResource("TanningSolution"),
                new ItemResource("BurnPlate"),
                new ItemResource("BrigantPlate"),
                new ItemResource("BlessedPlate"),
                new ItemResource("AngelFeather"),
                new ItemResource("Clasp"),
                new ItemResource("CrimsonFabric"),
                new ItemResource("CursedPlate"),
                new ItemResource("DevilScales"),
                new ItemResource("DragonLeather"),
                new ItemResource("Edem"),
                new ItemResource("ElongatedPlate"),
                new ItemResource("EnderCrystal"),
                new ItemResource("GodPlate"),
                new ItemResource("Hell"),
                new ItemResource("HellLether"),
                new ItemResource("HellShards"),
                new ItemResource("ImpSkin"),
                new ItemResource("KozanePlates"),
                new ItemResource("Laces"),
                new ItemResource("LamellarPlates"),
                new ItemResource("LaminarPlates"),
                new ItemResource("LeatherPlate"),
                new ItemResource("MagicLaces"),
                new ItemResource("MetalScales"),
                new ItemResource("PaperPlate"),
                new ItemResource("PressedPaper"),
                new ItemResource("PrimalFabric"),
                new ItemResource("ProcessedBone"),
                new ItemResource("QuiltedFabric"),
                new ItemResource("QuiltedMagicFabric"),
                new ItemResource("RunePaper"),
                new ItemResource("RougeMagicFabric"),
                new ItemResource("RuneSkin"),
                new ItemResource("SkinStripes"),
                new ItemResource("SpatialFabric"),
                new ItemResource("Shadow"),
                new ItemResource("PaperStack"),
                new ItemResource("SpatialKozanePlates"),
                new ItemResource("StitchedFabric"),
                new ItemResource("SuperdensePlate"),
                new ItemResource("TannedLeather"),
                new ItemResource("UnicornLether"),
                new ItemResource("MagicRivet")
        );
    }
}
