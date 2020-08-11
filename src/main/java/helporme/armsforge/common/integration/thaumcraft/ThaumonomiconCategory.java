package helporme.armsforge.common.integration.thaumcraft;

import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchCategories;

public class ThaumonomiconCategory
{
    public static final String name = Version.modid;

    public static final ResourceLocation background = ResourceManager.get("textures/thaumonomicon/background.png");
    public static final ResourceLocation icon = ResourceManager.get("textures/thaumonomicon/icon.png");

    public static void register()
    {
        ResearchCategories.registerCategory(name, icon, background);
    }
}
