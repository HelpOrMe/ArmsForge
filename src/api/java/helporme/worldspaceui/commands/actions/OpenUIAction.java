package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.commands.helper.ChatHelper;
import helporme.worldspaceui.commands.parser.CommandParser;
import helporme.worldspaceui.commands.parser.ParseException;
import helporme.worldspaceui.types.Vector3d;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.command.ICommandSender;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class OpenUIAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "open", "o" };
    }

    @Override
    public Class<?>[] getRequiredArgumentTypes()
    {
        return new Class[] { Integer.class, Vector3d.class, Integer.class };
    }

    @Override
    public int getMinArgumentsCount()
    {
        return 3;
    }

    @Override
    public String getHelpString()
    {
        return "/ui open <UIid> <x> <y> <z> <constructor index> [Arguments] Use /ui list to get more info";
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args, String[] rawArgs)
    {
        int uiId = (int)args[0];
        if (!WorldSpaceUIServer.map.uiIdToUIClassName.containsKey(uiId))
        {
            ChatHelper.printError(sender, "Invalid UIid. Use /ui list to get list of UIid");
        }

        UILocation location = new UILocation((Vector3d)args[1], sender.getEntityWorld().provider.dimensionId);
        try
        {
            Class<?> cls = Class.forName(WorldSpaceUIServer.map.uiIdToUIClassName.get(uiId));
            Constructor<?> constructor = cls.getConstructors()[(int)args[2]];
            String[] constructorArgs = Arrays.copyOfRange(rawArgs, 5, rawArgs.length);
            CommandParser parser = new CommandParser(sender, constructorArgs, constructor.getParameterTypes());
            UI ui = (UI)constructor.newInstance(parser.parse());
            WorldSpaceUIServer.openUI(ui, location, 30);
        }
        catch (ParseException e)
        {
            ChatHelper.printError(sender, "Invalid constructor arguments");
        }
        catch (ClassNotFoundException e)
        {
            WorldSpaceUIServer.logger.error("Unable to get class by UIid " + uiId, e);
            ChatHelper.printError(sender, "Invalid UI class, how do you get UIid?");
        }
        catch (ReflectiveOperationException | ArrayIndexOutOfBoundsException e)
        {
            ChatHelper.printError(sender, "Invalid constructor index");
        }
    }
}
