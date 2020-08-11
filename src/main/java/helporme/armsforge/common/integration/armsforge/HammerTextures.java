package helporme.armsforge.common.integration.armsforge;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.recipes.hammer.HammerTypes;
import helporme.armsforge.common.core.Version;

public class HammerTextures
{
    public static void addAll()
    {
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallIron, Version.modid + ":itextures/tems/tools/SmallhammerIron.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallSteel, Version.modid + ":textures/items/tools/SmallhammerSteel.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.smallThaum, Version.modid + ":textures/items/tools/SmallhammerThaum.png");

        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumIron, Version.modid + ":textures/items/tools/MediumhammerIron.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumSteel, Version.modid + ":textures/items/tools/MediumhammerSteel.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.mediumThaum, Version.modid + ":textures/items/tools/MediumhammerThaum.png");

        ArmsForgeApi.attachHammerTexture(HammerTypes.bigIron, Version.modid + ":textures/items/tools/BighammerIron.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.bigSteel, Version.modid + ":textures/items/tools/BighammerSteel.png");
        ArmsForgeApi.attachHammerTexture(HammerTypes.bigThaum, Version.modid + ":textures/items/tools/BighammerThaum.png");
    }
}
