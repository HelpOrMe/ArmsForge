package helporme.worldspaceui.commands;

import net.minecraft.command.ICommandSender;

public abstract class UICommandAction
{
    public abstract String[] getNames();

    public Class<?>[] getRequiredArgumentTypes()
    {
        return new Class[0];
    }

    public int getMinArgumentsCount()
    {
        return 0;
    }

    public String getHelpString()
    {
        return "";
    }

    public abstract void doAction(ICommandSender sender, Object args);
}
