package helporme.worldspaceui.commands;

import helporme.worldspaceui.WorldSpaceUIServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class ListUICommand extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "listUI";
    }

    @Override
    public List<String> getCommandAliases()
    {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("lui");
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/listUI";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        for (int uiId : WorldSpaceUIServer.map.uiIdToUIClassName.keySet())
        {
            String message = uiId + " >> " + WorldSpaceUIServer.map.uiIdToUIClassName.get(uiId);
            sender.addChatMessage(new ChatComponentText(message));
        }
    }
}
