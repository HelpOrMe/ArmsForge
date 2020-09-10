package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.ui.UITargetBlock;
import net.minecraft.command.ICommandSender;

public class CreateUIAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "create", "c" };
    }

    @Override
    public Class<?>[] getRequiredArgumentTypes()
    {
        return new Class[] { int.class, UITargetBlock.class, String.class};
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args)
    {
        WorldSpaceUIServer.logger.debug("Typeofargs: " + args[0].toString() + " " + args[1].toString());
    }
}
