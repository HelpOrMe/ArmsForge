package helporme.worldspaceui.commands;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import helporme.worldspaceui.ui.UITargetBlock;
import net.minecraft.command.ICommand;

import java.util.HashMap;
import java.util.Map;

public final class Commands
{
    public final ICommand[] commands = new ICommand[] { new CreateUICommand(), new ListUICommand() };
    public final Map<String, String> cachedUITargetClasses = new HashMap<>();

    public void register(FMLServerStartingEvent event)
    {
        for (ICommand command : commands)
        {
            event.registerServerCommand(command);
        }
    }

    public void cacheDefaultUITargets()
    {
        cachedUITargetClasses.put("block", UITargetBlock.class.getName());
    }
}
