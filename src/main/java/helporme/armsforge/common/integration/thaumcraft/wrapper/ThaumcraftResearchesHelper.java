package helporme.armsforge.common.integration.thaumcraft.wrapper;

import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.ResourceManager;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchPage;

public final class ThaumcraftResearchesHelper
{
    public static ResourceLocation getResearchIcon(String key)
    {
        String path = "textures/thaumonomicon/researches/" + key + "ResearchIcon.png";
        return ResourceManager.get(path);
    }

    public static ResearchPage[] getLocalizedTextPages(String key, int count)
    {
        ResearchPage[] pages = new ResearchPage[count];
        for (int i = 0; i < count; i++)
        {
            pages[i] = getTextPage(key, i + 1);
        }
        return pages;
    }

    public static ResearchPage getTextPage(String key, int pageIndex)
    {
        return new ResearchPage(getPageId(key, pageIndex));
    }

    public static String getPageId(String key, int pageIndex)
    {
        return "tc.research_page." + key + "." + pageIndex;
    }
}
