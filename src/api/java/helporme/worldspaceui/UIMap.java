package helporme.worldspaceui;

import helporme.worldspaceui.ui.UI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class UIMap
{
    protected int lastUniqueUIId = -1;
    protected int lastUIid = -1;

    public final Map<Integer, String> uiIdToUIClassName = new HashMap<>();
    public final Map<String, Integer> uiClassToUIid = new HashMap<>();
    public final Map<Integer, UI> uiPool = new HashMap<>();

    public void registerUI(Class<? extends UI> uiClass)
    {
        if (!hasEmptyConstructor(uiClass))
        {
            throw new IllegalArgumentException("Empty constructor not found: " + uiClass.getName());
        }

        String uiClassName = uiClass.getName();
        lastUIid++;
        uiIdToUIClassName.put(lastUIid, uiClassName);
        uiClassToUIid.put(uiClassName, lastUIid);
    }

    public boolean hasEmptyConstructor(Class<? extends UI> uiClass)
    {
        for (Constructor<?> constructor : uiClass.getConstructors())
        {
            if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterCount() == 0)
            {
                return true;
            }
        }
        return false;
    }

    public int getNextUniqueUIid()
    {
        lastUniqueUIId++;
        return lastUniqueUIId;
    }
}
