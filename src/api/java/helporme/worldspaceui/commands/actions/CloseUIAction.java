package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.commands.helper.ChatHelper;
import net.minecraft.command.ICommandSender;

public class CloseUIAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "close", "c" };
    }

    @Override
    public Class<?>[] getRequiredArgumentTypes()
    {
        return new Class[] { Integer.class };
    }

    @Override
    public int getMinArgumentsCount()
    {
        return 1;
    }

    @Override
    public String getHelpString()
    {
        return "/ui close <UniqueUIid> Use /ui map to get info about opened UIes";
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args, String[] rawArgs)
    {
        int uniqueUIid = (int)args[0];
        if (!WorldSpaceUIServer.map.uiPool.containsKey(uniqueUIid))
        {
            ChatHelper.printError(sender, "Invalid UniqueUIid");
            return;
        }
        WorldSpaceUIServer.closeUI(uniqueUIid);
        ChatHelper.print(sender, "UI with uniqueId §a"+uniqueUIid+"§r has been closed");
    }
}
