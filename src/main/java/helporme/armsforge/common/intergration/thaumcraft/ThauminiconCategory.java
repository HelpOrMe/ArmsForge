package helporme.armsforge.common.intergration.thaumcraft;

import helporme.armsforge.common.core.Version;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;

public class ThauminiconCategory
{
    public static final String name = Version.modid;

    public static final ResourceLocation background = new ResourceLocation(Version.modid, "textures/thauminicon/background.png");
    public static final ResourceLocation icon = new ResourceLocation(Version.modid, "textures/thauminicon/icon.png");

    public static void register()
    {
        ResearchCategories.registerCategory(name, icon, background);
    }
}
