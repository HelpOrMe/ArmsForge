package helporme.worldspaceui.commands.actions;

import com.google.common.collect.Lists;
import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.commands.helper.ChatHelper;
import net.minecraft.command.ICommandSender;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIListAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "list", "l" };
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args, String[] rawArgs)
    {
        List<Map.Entry<Integer, Class<?>>> entries = Lists.newArrayList(getAllUIClasses().entrySet());
        for (int i = 0; i < entries.size(); i++)
        {
            int uiId = entries.get(i).getKey();
            Class<?> cls = entries.get(i).getValue();

            if (i > 0)
            {
                ChatHelper.printSep(sender);
            }
            ChatHelper.print(sender, "§o" + cls.getName() + " §aUIid#" + uiId);
            ChatHelper.print(sender, "Constructors: ");
            printConstructors(sender, cls);
        }
    }

    private Map<Integer, Class<?>> getAllUIClasses()
    {
        Map<Integer, Class<?>> classMap = new HashMap<>();
        for (int uiId : WorldSpaceUIServer.map.uiIdToUIClassName.keySet())
        {
            try
            {
                classMap.put(uiId, Class.forName(WorldSpaceUIServer.map.uiIdToUIClassName.get(uiId)));
            }
            catch (ClassNotFoundException e)
            {
                WorldSpaceUIServer.logger.error("Unable to get class from UIid " + uiId + "!", e);
            }
        }
        return classMap;
    }

    private void printConstructors(ICommandSender sender, Class<?> cls)
    {
        Constructor<?>[] constructors = cls.getConstructors();
        for (int i = 0; i < constructors.length; i++)
        {
            Constructor<?> constructor = constructors[i];
            if (Modifier.isPublic(constructor.getModifiers()))
            {
                Parameter[] params = constructor.getParameters();
                String[] paramNames = new String[params.length];
                for (int j = 0; j < params.length; j++)
                {
                    paramNames[j] = params[j].getType().getSimpleName() + "#" + params[j].getName();
                }
                ChatHelper.print(sender, "§7" + i + ".§r " + cls.getSimpleName() + "(" + String.join(", ", paramNames) + ")");
            }
        }
    }
}
