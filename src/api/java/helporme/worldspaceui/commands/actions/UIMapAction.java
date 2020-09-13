package helporme.worldspaceui.commands.actions;

import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.commands.UICommandAction;
import helporme.worldspaceui.commands.helper.ChatHelper;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.*;

public class UIMapAction extends UICommandAction
{
    @Override
    public String[] getNames()
    {
        return new String[] { "map", "m" };
    }

    @Override
    public void doAction(ICommandSender sender, Object[] args, String[] rawArgs)
    {
        ChatHelper.printSep(sender);
        ChatHelper.print(sender, "Locations §o§7(uiUniqueId >> Location)");
        for (Map.Entry<Integer, UILocation> entry : WorldSpaceUIServer.map.uiToLocation.entrySet())
        {
            ChatHelper.print(sender, "§a" + entry.getKey() + "§r >> " + entry.getValue());
        }

        ChatHelper.printSep(sender);
        ChatHelper.print(sender, "Users §o§7(uiUniqueId >> Users)");
        for (Map.Entry<Integer, Collection<EntityPlayerMP>> entry : WorldSpaceUIServer.map.uiPlayers.asMap().entrySet())
        {
            Collection<String> playerNames = new ArrayList<>();
            for (EntityPlayerMP player : entry.getValue())
            {
                playerNames.add(player.getDisplayName());
            }
            ChatHelper.print(sender, "§a" + entry.getKey() + "§r >> " + String.join(", ", playerNames));
        }

        ChatHelper.printSep(sender);
        ChatHelper.print(sender, "UI pool §o§7(uiUniqueId >> UI)");
        for (Map.Entry<Integer, UI> entry : WorldSpaceUIServer.map.uiPool.entrySet())
        {
            ChatHelper.print(sender, "§a" + entry.getKey() + "§r >> " + entry.getValue().getClass().getSimpleName());
        }
    }

}
