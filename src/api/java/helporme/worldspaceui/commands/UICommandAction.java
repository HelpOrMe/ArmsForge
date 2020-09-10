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
        StringBuilder stringBuilder = new StringBuilder("/ui " + getNames()[0]);
        Class<?>[] classes = getRequiredArgumentTypes();
        int min = getMinArgumentsCount();

        for (int i = 0; i < classes.length; i++)
        {
            String leftChar = i < min ? " <" : " [";
            String rightChar = i < min ? ">" : "]";
            stringBuilder.append(leftChar).append(classes[i].getSimpleName()).append(rightChar);
        }
        return stringBuilder.toString();
    }

    public abstract void doAction(ICommandSender sender, Object[] args);
}
