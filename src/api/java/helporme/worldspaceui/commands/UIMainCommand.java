package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.parser.CommandParser;
import helporme.worldspaceui.commands.parser.ParseException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;

public final class UIMainCommand extends CommandBase
{
    public final String[] specialPrefixes = new String[] {"~"};

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
            String actionHelpMessage = action.getHelpString();
            if (!actionHelpMessage.isEmpty())
            {
                print(sender, actionHelpMessage);
            }
            printError(sender, "Invalid arguments count");
            return;
        }

        Class<?>[] requiredClasses = action.getRequiredArgumentTypes();
        CommandParser parser = new CommandParser(sender, args, requiredClasses);

        try
        {
            action.doAction(sender, parser.parse());
        }
        catch (ParseException e)
        {
            printError(sender, e.getMessage());
        }
    }

    private void printHelpMessage(ICommandSender sender)
    {
        print(sender,"~HelpMessage~");
    }

    private void printError(ICommandSender sender, String text)
    {
        print(sender, "§c" + text);
    }

    private void print(ICommandSender sender, String text)
    {
        sender.addChatMessage(new ChatComponentText(text));
    }
}
