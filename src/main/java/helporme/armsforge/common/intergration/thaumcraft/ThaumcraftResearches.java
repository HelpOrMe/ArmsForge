package helporme.armsforge.common.intergration.thaumcraft;

import helporme.armsforge.common.core.Version;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import java.util.HashMap;

public final class ThaumcraftResearches
{
    private static HashMap<String, ResearchItem> researches = new HashMap<String, ResearchItem>();

    public static void createDefaultResearches()
    {
        addResearch(createOpeningResearch());
    }

    public static void addResearch(ResearchItem research)
    {
        researches.put(research.key, research);
    }

    public static ResearchItem createOpeningResearch()
    {
        String key = "opening";
        ResearchItem research = new ResearchItem(key, ThauminiconCategory.name, new AspectList(), 0, 0, 0, ThauminiconCategory.icon);
        research.setPages(getTextPages(key, 2));
        research.setAutoUnlock();
        research.setSpecial();
        return research;
    }

    public static void registerResearches()
    {
        for (ResearchItem research : researches.values())
        {
            research.registerResearchItem();
        }
    }

    private static ResearchPage[] getTextPages(String key, int pagesCount)
    {
        ResearchPage[] pages = new ResearchPage[pagesCount];
        for (int i = 0; i < pagesCount; i++)
        {
            pages[i] = getTextPage(key, i + 1);
        }
        return pages;
    }

    private static ResearchPage getTextPage(String key, int pageIndex)
    {
        return new ResearchPage(getPageId(key, pageIndex));
    }

    private static String getPageId(String key, int pageIndex)
    {
        return "tc.research_page." + key + "." + pageIndex;
    }
}
