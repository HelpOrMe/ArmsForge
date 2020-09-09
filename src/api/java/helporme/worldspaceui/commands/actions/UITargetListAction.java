package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Map;

public class UITargetListAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "targetList", "tl" };
    }

    @Override
    public void doAction(ICommandSender sender, Object args)
    {
        for (Map.Entry<String, String> entry : WorldSpaceUIServer.commands.supportedClasses.entrySet())
        {
            String message = entry.getValue() + " >> " + entry.getKey();
            sender.addChatMessage(new ChatComponentText(message));
        }
    }
}
