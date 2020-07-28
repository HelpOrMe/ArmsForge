package helporme.armsforge.common.integration.thaumcraft;

import helporme.armsforge.common.integration.thaumcraft.wrapper.ResearchItemExtended;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

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
        ResearchItemExtended research = new ResearchItemExtended(key, new AspectList(), 0, 0, 0);
        research.addLocalizedPages(2);
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
}
