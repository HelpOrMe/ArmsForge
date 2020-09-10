package helporme.worldspaceui.commands;

import net.minecraft.command.ICommandSender;

public interface ICommandSupportedObj
{
    Class<?>[] getRequiredArgumentTypes();
    void initFromCommand(ICommandSender sender, Object[] args);
}
