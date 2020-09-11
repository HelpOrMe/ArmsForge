package helporme.worldspaceui;

import helporme.worldspaceui.ui.UI;

import java.util.HashMap;
import java.util.Map;

public class UIMap
{
    protected int lastUniqueUIId = -1;
    protected int lastUIid = -1;

    public final Map<Integer, String> uiIdToUIClassName = new HashMap<>();
    public final Map<String, Integer> uiClassToUIid = new HashMap<>();
    public final Map<Integer, UI> uiPool = new HashMap<>();

    public void attachUIid(String UIClassName)
    {
        lastUIid++;
        uiIdToUIClassName.put(lastUIid, UIClassName);
        uiClassToUIid.put(UIClassName, lastUIid);
    }

    public int getNextUniqueUIId()
    {
        lastUniqueUIId++;
        return lastUniqueUIId;
    }
}
