package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class UIListAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "list", "l" };
    }

    @Override
    public void doAction(ICommandSender sender, Object args)
    {
        for (int uiId : WorldSpaceUIServer.map.uiIdToUIClassName.keySet())
        {
            String message = uiId + " >> " + WorldSpaceUIServer.map.uiIdToUIClassName.get(uiId);
            sender.addChatMessage(new ChatComponentText(message));
        }
    }
}
