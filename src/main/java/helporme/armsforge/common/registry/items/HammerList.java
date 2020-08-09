package helporme.armsforge.common.registry.items;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.common.items.ItemHammer;
import helporme.armsforge.api.recipes.hammer.HammerTypes;

public class HammerList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new ItemHammer("SmallhammerIron", HammerTypes.smallIron, 1000),
                new ItemHammer("SmallhammerSteel", HammerTypes.smallSteel, 4000),
                new ItemHammer("SmallhammerThaum", HammerTypes.smallThaum, 7000),

                new ItemHammer("MediumhammerIron", HammerTypes.mediumIron, 1000),
                new ItemHammer("MediumhammerSteel", HammerTypes.mediumSteel, 4000),
                new ItemHammer("MediumhammerThaum", HammerTypes.mediumThaum, 7000),

                new ItemHammer("BighammerIron", HammerTypes.bigIron, 1000),
                new ItemHammer("BighammerSteel", HammerTypes.bigSteel, 4000),
                new ItemHammer("BighammerThaum", HammerTypes.bigThaum, 7000)
        );

        //TODO: Move to integration manager
        ArmsForgeApi.addHammer(items.get("SmallhammerIron"), HammerTypes.smallIron);
        ArmsForgeApi.addHammer(items.get("SmallhammerSteel"), HammerTypes.smallSteel);
        ArmsForgeApi.addHammer(items.get("SmallhammerThaum"), HammerTypes.smallThaum);

        ArmsForgeApi.addHammer(items.get("MediumhammerIron"), HammerTypes.mediumIron);
        ArmsForgeApi.addHammer(items.get("MediumhammerSteel"), HammerTypes.mediumSteel);
        ArmsForgeApi.addHammer(items.get("MediumhammerThaum"), HammerTypes.mediumThaum);

        ArmsForgeApi.addHammer(items.get("BighammerIron"), HammerTypes.bigIron);
        ArmsForgeApi.addHammer(items.get("BighammerSteel"), HammerTypes.bigSteel);
        ArmsForgeApi.addHammer(items.get("BighammerThaum"), HammerTypes.bigThaum);
    }
}
