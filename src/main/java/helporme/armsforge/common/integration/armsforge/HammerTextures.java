package helporme.armsforge.common.integration.armsforge;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.recipes.hammer.HammerTypes;
import helporme.armsforge.common.core.Version;

public class HammerTextures
{
    public static void addAll()
    {
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallIron, Version.modid + ":SmallhammerIron");
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallSteel, Version.modid + ":SmallhammerSteel");
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallThaum, Version.modid + ":SmallhammerThaum");

        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumIron, Version.modid + ":MdeiumhammerIron");
        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumSteel, Version.modid + ":MdeiumhammerSteel");
        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumThaum, Version.modid + ":MdeiumhammerThaum");

        ArmsForgeApi.attachHammerTexture(HammerTypes.bigIron, Version.modid + ":BighammerIron");
        ArmsForgeApi.attachHammerTexture(HammerTypes.bigSteel, Version.modid + ":BighammerSteel");
        ArmsForgeApi.attachHammerTexture(HammerTypes.bigThaum, Version.modid + ":BighammerThaum");
    }
}
