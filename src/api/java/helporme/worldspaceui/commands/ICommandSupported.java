package helporme.worldspaceui.commands;

import net.minecraft.command.ICommandSender;

public interface ICommandSupported
{
    void initFromCommand(ICommandSender sender, String arg);
    void initFromCommand(String... args);
}
