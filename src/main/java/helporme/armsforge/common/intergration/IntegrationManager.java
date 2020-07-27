package helporme.armsforge.common.intergration;

import helporme.armsforge.common.intergration.thaumcraft.ThaumcraftResearches;
import helporme.armsforge.common.intergration.thaumcraft.ThauminiconCategory;

public class IntegrationManager
{
    public static void registerThaumcraft()
    {
        ThauminiconCategory.register();
        ThaumcraftResearches.createDefaultResearches();
        ThaumcraftResearches.registerResearches();
    }
}
