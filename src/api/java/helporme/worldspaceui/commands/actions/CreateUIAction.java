package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.commands.UICommandAction;
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
        return new Class[] { int.class };
    }

    @Override
    public void doAction(ICommandSender sender, Object args) {

    }
}
