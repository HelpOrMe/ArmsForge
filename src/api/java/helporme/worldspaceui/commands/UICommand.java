package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class UICommand extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "ui";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/ui <Action> [args]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length == 0 || args[0].equalsIgnoreCase("help") ||
            !WorldSpaceUIServer.commands.actions.containsKey(args[0]))
        {
            printHelpString(sender);
            return;
        }
        invokeAction(sender, args);
    }

    public void invokeAction(ICommandSender sender, String[] args)
    {
        UICommandAction action = WorldSpaceUIServer.commands.actions.get(args[0]);
        if (!validateArgumentsCount(action, sender, args)) return;

        Class<?>[] requiredClasses = action.getRequiredArgumentTypes();
        Object[] objects = new Object[args.length - 1];

        for (int i = 1; i < requiredClasses.length; i++)
        {
            String arg = args[i];
            Object obj = tryToParsePrimalClass(requiredClasses[i], arg);
            if (obj == null && !WorldSpaceUIServer.commands.supportedClasses.containsKey(arg))
            {
                print(sender, "Unable to resolve \"" + arg + "\"");
                return;
            }
            objects[i] = obj;
        }

        action.doAction(sender, objects);
    }

    private boolean validateArgumentsCount(UICommandAction action, ICommandSender sender, String[] args)
    {
        if (action.getMinArgumentsCount() < args.length - 1)
        {
            printArgumentsError(sender);
            String helpString = action.getHelpString();
            if (!helpString.isEmpty())
            {
                print(sender, helpString);
                return false;
            }
        }
        return true;
    }

    private Object tryToParsePrimalClass(Class<?> cls, String str)
    {
        try
        {
            switch (cls.getSimpleName().toLowerCase())
            {
                case "int": case "Integer":
                    return Integer.parseInt(str);
                case "float":
                    return Float.parseFloat(str);
                case "boolean":
                    return Boolean.parseBoolean(str);
                case "string":
                    return str;
            }
        }
        catch (NumberFormatException ignored) { }
        return null;
    }

    private void printHelpString(ICommandSender sender)
    {
        //
    }

    private void printArgumentsError(ICommandSender sender)
    {
        //
    }

    private void print(ICommandSender sender, String text)
    {
        sender.addChatMessage(new ChatComponentText(text));
    }
}
