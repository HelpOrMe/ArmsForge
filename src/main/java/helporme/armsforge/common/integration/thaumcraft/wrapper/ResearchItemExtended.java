package helporme.armsforge.common.integration.thaumcraft.wrapper;

import helporme.armsforge.common.integration.thaumcraft.ThauminiconCategory;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResearchItemExtended extends ResearchItem
{
    public ResearchItemExtended(String key, AspectList tags, int col, int row, int complex)
    {
        super(key, ThauminiconCategory.name, tags, col, row, complex, ThaumcraftResearchesHelper.getResearchIcon(key));
        setPages(); // Set empty array instead of null
    }

    public void addLocalizedPages(int count)
    {
        addPages(ThaumcraftResearchesHelper.getLocalizedTextPages(key, count));
    }

    public void addPages(ResearchPage... pages)
    {
        setPages(pages);
        // List<ResearchPage> currentPages = new ArrayList<ResearchPage>(Arrays.asList(getPages()));
        // currentPages.addAll(Arrays.asList(pages));
        // setPages((ResearchPage[])currentPages.toArray());
    }
}
