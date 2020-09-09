package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public final class UIMainCommand extends CommandBase
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
            printHelpMessage(sender);
            return;
        }

        String[] actionArgs = Arrays.copyOfRange(args, 1, args.length);
        invokeAction(sender, args[0], actionArgs);
    }

    public void invokeAction(ICommandSender sender, String actionName, String[] args)
    {
        UICommandAction action = WorldSpaceUIServer.commands.actions.get(actionName);

        if (action.getMinArgumentsCount() > args.length)
        {
            printActionArgumentsError(sender, action);
            return;
        }

        Class<?>[] requiredClasses = action.getRequiredArgumentTypes();
        Object[] objects = new Object[args.length - 1];

        for (int i = 1; i < requiredClasses.length; i++)
        {
            String arg = args[i];
            Object obj = parsePrimalClass(requiredClasses[i], arg);
            if (obj == null && !WorldSpaceUIServer.commands.supportedClasses.containsKey(arg))
            {
                print(sender, "Unable to resolve \"" + arg + "\"");
                return;
            }
            objects[i] = obj;
        }

        action.doAction(sender, objects);
    }

    private boolean parseArguments(List<Object> parsedArgs, String[] args, Class<?>[] types)
    {
        int argsToParseCount = Math.min(args.length, types.length);
        for (int i = 0; i < argsToParseCount; i++)
        {
            if (types[i].isPrimitive())
            {
                parsePrimitiveClass(types[i], args[i]);
            }
        }
    }

    private Object parsePrimitiveClass(Class<?> cls, String value)
    {
        if (Boolean.class == cls ) return Boolean.parseBoolean( value );
        if (Byte.class == cls ) return Byte.parseByte(value);
        if (Short.class == cls ) return Short.parseShort(value);
        if (Integer.class == cls ) return Integer.parseInt(value);
        if (Long.class == cls ) return Long.parseLong(value);
        if (Float.class == cls ) return Float.parseFloat(value);
        if (Double.class == cls ) return Double.parseDouble(value);
        return value;
    }

    private void printHelpMessage(ICommandSender sender)
    {
        //
    }

    private void printActionArgumentsError(ICommandSender sender, UICommandAction action, String... targetArguments)
    {
        //
    }

    private void print(ICommandSender sender, String text)
    {
        sender.addChatMessage(new ChatComponentText(text));
    }
}
