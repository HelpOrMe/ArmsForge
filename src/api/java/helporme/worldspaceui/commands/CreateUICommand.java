package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.*;

public class CreateUICommand extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "createUI";
    }

    @Override
    public List<String> getCommandAliases()
    {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("cui");
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/createUI <UIid> <UITarget> [UILocation]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (!validate(sender, args)) return;

        sender.addChatMessage(new ChatComponentText("createUI called with args " + String.join(", ", args)));
    }

    public boolean validate(ICommandSender sender, String[] args)
    {
        if (args.length < 2)
        {
            sender.addChatMessage(new ChatComponentText("§cInvalid arguments count"));
            return false;
        }
        if (WorldSpaceUIServer.map.uiIdToUIClassName.containsValue(args[0]))
        {
            sender.addChatMessage(new ChatComponentText("§cInvalid UIid"));
            return false;
        }
        if (!WorldSpaceUIServer.commands.cachedUITargetClasses.containsKey(args[1]))
        {
            sender.addChatMessage(new ChatComponentText("§cInvalid UITarget"));
            return false;
        }
        return true;
    }
}
