package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.types.Vector3i;
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
        return new Class[] { Integer.class, String.class, Vector3i.class};
    }

    @Override
    public int getMinArgumentsCount()
    {
        return 2;
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args)
    {
        StringBuilder stringBuilder = new StringBuilder("Arg types (" + args.length + "): ");
        for (Object arg : args)
        {
            if (arg != null)
            {
                stringBuilder.append(arg.getClass().getSimpleName()).append(" ");
            }
        }
        WorldSpaceUIServer.logger.info(stringBuilder.toString());
    }
}
