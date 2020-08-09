package helporme.armsforge.common.integration;

import helporme.armsforge.common.integration.armsforge.RecipeIcons;
import helporme.armsforge.common.integration.thaumcraft.ThaumcraftResearches;
import helporme.armsforge.common.integration.thaumcraft.ThaumonomiconCategory;

public class IntegrationManager
{
    public static void registerArmsForge()
    {
        RecipeIcons.addAll();
    }

    public static void registerThaumcraft()
    {
        ThaumonomiconCategory.register();
        ThaumcraftResearches.createDefaultResearches();
        ThaumcraftResearches.registerResearches();
    }
}
