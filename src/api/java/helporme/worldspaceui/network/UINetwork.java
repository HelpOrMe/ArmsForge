package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.network.packets.CloseUIPacket;
import helporme.worldspaceui.network.packets.OpenUIPacket;
import helporme.worldspaceui.network.packets.UISyncPacket;
import helporme.worldspaceui.network.targets.ITargetFilter;
import helporme.worldspaceui.types.Vector3d;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import helporme.worldspaceui.ui.UISynced;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class UINetwork extends SimpleNetworkWrapper
{
    protected int lastId = 1;

    public UINetwork()
    {
        super("WorldSpaceUI");
    }

    public void init()
    {
        registerMessage(OpenUIPacket.class, OpenUIPacket.class, lastId++, Side.CLIENT);
        registerMessage(CloseUIPacket.class, CloseUIPacket.class, lastId++, Side.CLIENT);
        registerMessage(UISyncPacket.class, UISyncPacket.class, lastId++, Side.CLIENT);
    }

    public void sendUIOpen(UI ui, UILocation location, double range, ITargetFilter targetFilter)
    {
        sendUIOpen(ui, targetFilter.filterPlayers(location, getPlayersInRange(location, range)));
    }

    public void sendUIOpen(UI ui, UILocation location, double range)
    {
        sendUIOpen(ui, getPlayersInRange(location, range));
    }

    private EntityPlayerMP[] getPlayersInRange(UILocation location, double range)
    {
        WorldServer world = DimensionManager.getWorld(location.dimension);

        Vector3d pos = location.position;
        AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(
                pos.x - range, 0, pos.z - range,
                pos.x + range, 255, pos.z + range);

        List BBEntities = world.getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox);
        EntityPlayerMP[] players = (EntityPlayerMP[])BBEntities.toArray(new EntityPlayerMP[0]);
        return players;
    }

    public void sendUIOpen(UI ui, EntityPlayerMP... players)
    {
        LogManager.getLogger().info("Send ui open " + players.length);
        for (EntityPlayerMP player : players)
        {
            sendTo(new OpenUIPacket(ui), player);
            LogManager.getLogger().info("Add ui player to " + ui.uniqueId);
            WorldSpaceUIServer.map.uiPlayers.put(ui.uniqueId, player);
        }
    }

    public void sendUIClose(int uiUniqueIndex, ITargetFilter targetFilter)
    {
        EntityPlayerMP[] players = WorldSpaceUIServer.map.uiPlayers.get(uiUniqueIndex).toArray(new EntityPlayerMP[0]);
        players = targetFilter.filterPlayers(WorldSpaceUIServer.map.uiToLocation.get(uiUniqueIndex), players);
        sendUIClose(uiUniqueIndex, players);
    }

    public void sendUIClose(int uiUniqueIndex)
    {
        EntityPlayerMP[] players = WorldSpaceUIServer.map.uiPlayers.get(uiUniqueIndex).toArray(new EntityPlayerMP[0]);
        sendUIClose(uiUniqueIndex, players);
    }

    public void sendUIClose(int uiUniqueIndex, EntityPlayerMP... players)
    {
        for (EntityPlayerMP player : players)
        {
            if (WorldSpaceUIServer.map.uiPlayers.containsEntry(uiUniqueIndex, player))
            {
                sendTo(new CloseUIPacket(uiUniqueIndex), player);
                WorldSpaceUIServer.map.uiPlayers.remove(uiUniqueIndex, player);
            }
            else
            {
                WorldSpaceUIServer.logger.error("Unable to close UI on client " + player);
            }
        }
    }

    public void syncUI(UISynced ui)
    {
        if (ui.getSyncFields().size() > 0)
        {
            for (EntityPlayerMP player : WorldSpaceUIServer.map.uiPlayers.get(ui.uniqueId))
            {
                sendTo(new UISyncPacket(ui), player);
            }
            return;
        }
        WorldSpaceUIServer.logger.warn("Trying to sync UI without `@Sync` fields; " + ui.getClass().getName());
    }
}
