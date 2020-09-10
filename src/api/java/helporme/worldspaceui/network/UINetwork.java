package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import helporme.worldspaceui.WorldSpaceUIServer;
import helporme.worldspaceui.network.packets.CloseUIPacket;
import helporme.worldspaceui.network.packets.OpenUIPacket;
import helporme.worldspaceui.network.targets.ITargetFilter;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

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
    }

    public void sendUIOpen(UI ui, UILocation location, ITargetFilter targetFilter)
    {
        WorldServer world = DimensionManager.getWorld(location.dimension);

        double range = targetFilter.getRange();
        AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(-range, -range, -range, range, range, range);

        List BBEntities = world.getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox);
        EntityPlayerMP[] players = (EntityPlayerMP[])BBEntities.toArray(new EntityPlayerMP[0]);
        sendUIOpen(ui, players);
    }

    public void sendUIOpen(UI ui, EntityPlayerMP... players)
    {
        for (EntityPlayerMP player : players)
        {
            sendTo(new OpenUIPacket(ui), player);
            WorldSpaceUIServer.map.uiPlayers.put(ui.uniqueId, player);
        }
    }

    public void sendUIClose(int uiUniqueIndex, ITargetFilter targetFilter)
    {
        EntityPlayerMP[] uiPlayers = WorldSpaceUIServer.map.uiPlayers.get(uiUniqueIndex).toArray(new EntityPlayerMP[0]);
        for (EntityPlayerMP player : uiPlayers)
        {
            if (targetFilter.canSendTo(player))
            {
                sendTo(new CloseUIPacket(uiUniqueIndex), player);
            }
            WorldSpaceUIServer.map.uiPlayers.remove(uiUniqueIndex, player);
        }
    }

//    public void syncUILocation(UI ui)
//    {
//        for (EntityPlayerMP player : WorldSpaceUIServer.map.uiPlayers.get(ui.uniqueId))
//        {
//            sendTo(new SyncUILocationPacket(ui.uniqueId, ui.location), player);
//        }
//    }
}
