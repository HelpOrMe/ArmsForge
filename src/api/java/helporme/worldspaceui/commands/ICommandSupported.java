package helporme.worldspaceui.commands;

import net.minecraft.command.ICommandSender;

public interface ICommandSupported<T>
{
    void initFromCommand(ICommandSender sender, String arg);
}
