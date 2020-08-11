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
    }
}
