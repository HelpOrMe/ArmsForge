package helporme.worldspaceui.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import helporme.worldspaceui.WorldSpaceUIServer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public abstract class UISynced extends UI
{
    private final Set<Field> cachedSyncFields = new HashSet<>();

    protected void sync()
    {
        if (UILayout.getCurrentUI() == this)
        {
            if (UILayout.getCurrentMode().isServer())
            {
                WorldSpaceUIServer.network.syncUI(this);
            }
            return;
        }
        logger.error("UI.sync() can be called only from onUI method! Use `WorldSpaceUIServer.network.syncUI()`");
    }

    public List<Object> getSyncObjects()
    {
        Collection<Field> fields = getSyncFields();
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields)
        {
            try
            {
                fieldValues.add(field.get(this));
            }
            catch (IllegalAccessException e)
            {
                printFieldError("Unable to get field value for sync from ", field);
            }
        }
        return fieldValues;
    }

    public Collection<Field> getSyncFields()
    {
        if (cachedSyncFields.size() == 0)
        {
            cacheSyncFields();
        }
        return cachedSyncFields;
    }

    private void cacheSyncFields()
    {
        for (Field field : getClass().getFields())
        {
            for (Annotation annotation : field.getAnnotations())
            {
                if (annotation instanceof Sync)
                {
                    if (isValidSyncFieldClass(field.getType()))
                    {
                        cachedSyncFields.add(field);
                        break;
                    }
                    printFieldError("Invalid object type. Must be a primitive, string, or implement IMessage; ",
                            field);
                    break;
                }
            }
        }
    }

    public boolean isValidSyncFieldClass(Class<?> objCls)
    {
        return objCls.isPrimitive() || objCls == String.class || objCls.isAssignableFrom(IMessage.class);
    }

    private void printFieldError(String message,Field field)
    {
        WorldSpaceUIServer.logger.error(message + getClass().getName() + "#" + field.getName());
    }
}
