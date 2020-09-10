package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Map;

public class UISupportedListAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "supObjectsList", "sol", "sl", "ol" };
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args)
    {
        for (Map.Entry<String, String> entry : WorldSpaceUIServer.commands.supportedClasses.entrySet())
        {
            String message = entry.getValue() + " >> " + entry.getKey();
            sender.addChatMessage(new ChatComponentText(message));
        }
    }
}
