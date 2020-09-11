package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.helper.ChatHelper;
import helporme.worldspaceui.commands.parser.CommandParser;
import helporme.worldspaceui.commands.parser.ParseException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;

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
            !WorldSpaceUIServer.commands.isActionExists(args[0]))
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

        int minArgsCount = action.getMinArgumentsCount();
        if (minArgsCount > args.length)
        {
            String actionHelpMessage = action.getHelpString();
            ChatHelper.printError(sender, actionHelpMessage.isEmpty() ? "Invalid arguments count" : actionHelpMessage);
            return;
        }

        Class<?>[] requiredClasses = action.getRequiredArgumentTypes();
        int minArgsSize = Math.min(args.length, requiredClasses.length);
        requiredClasses = Arrays.copyOf(action.getRequiredArgumentTypes(), minArgsSize);

        CommandParser parser = new CommandParser(sender, args, requiredClasses);
        try
        {
            action.doAction(sender, parser.parse(), args);
        }
        catch (ParseException e)
        {
            ChatHelper.printError(sender, e.getMessage());
        }
    }

    private void printHelpMessage(ICommandSender sender)
    {
        ChatHelper.print(sender, "~HelpMessage~");
    }
}
