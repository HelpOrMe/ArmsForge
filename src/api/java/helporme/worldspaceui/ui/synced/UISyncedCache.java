package helporme.worldspaceui.ui.synced;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.ui.UI;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UISyncedCache
{
    private static final Multimap<Integer, Field> cachedUISyncFields = HashMultimap.create();

    public static List<Object> getSyncObjects(UI ui)
    {
        Collection<Field> fields = getSyncFields(ui);
        List<Object> fieldValues = new ArrayList<>();
        for (Field field : fields)
        {
            try
            {
                Object obj = field.get(ui);
                if (isValidObject(obj))
                {
                    fieldValues.add(obj);
                }
                else
                {
                    printFieldError("Invalid object type. Must be a primitive, string, or implement IMessage. ",
                            ui, field);
                }
            }
            catch (IllegalAccessException e)
            {
                printFieldError("Unable to get field value for sync from ", ui, field);
            }
        }
        return fieldValues;
    }

    public static Collection<Field> getSyncFields(UI ui)
    {
        if (containsCacheFor(ui))
        {
            return getCachedFields(ui.uniqueId);
        }

        List<Field> fields = new ArrayList<>();
        for (Field field : ui.getClass().getFields())
        {
            for (Annotation annotation : field.getAnnotations())
            {
                if (annotation instanceof Sync)
                {
                    fields.add(field);
                    break;
                }
            }
        }
        return fields;
    }

    public static boolean containsCacheFor(UI ui)
    {
        return cachedUISyncFields.containsKey(ui.uniqueId);
    }

    public static Collection<Field> getCachedFields(int uiUniqueId)
    {
        return cachedUISyncFields.get(uiUniqueId);
    }

    public static boolean isValidObject(Object obj)
    {
        Class<?> objCls = obj.getClass();
        return objCls.isPrimitive() || objCls == String.class || objCls.isAssignableFrom(IMessage.class);
    }

    private static void printFieldError(String message, UI ui, Field field)
    {
        WorldSpaceUIServer.logger.error(message +  ui.getClass().getName() + "#" + field.getName());
        cachedUISyncFields.remove(ui.uniqueId, field);
    }
}
