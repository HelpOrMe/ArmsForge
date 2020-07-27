package helporme.armsforge.common.integration;

import helporme.armsforge.common.integration.thaumcraft.ThaumcraftResearches;
import helporme.armsforge.common.integration.thaumcraft.ThauminiconCategory;

public class IntegrationManager
{
    public static void prepareThaumcraft()
    {
        ThaumcraftResearches.createDefaultResearches();
    }

    public static void registerThaumcraft()
    {
        ThauminiconCategory.register();
        ThaumcraftResearches.registerResearches();
    }
}
