package helporme.worldspaceui.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import helporme.worldspaceui.ITargetFilter;
import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.ui.UI;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

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

    public void sendUIOpen(UI ui, ITargetFilter targetFilter)
    {
        WorldServer world = DimensionManager.getWorld(ui.location.dimension);
        double range = targetFilter.getRange();
        AxisAlignedBB boundingBox =  AxisAlignedBB.getBoundingBox(-range, -range, -range, range, range, range);

        for (Object obj : world.getEntitiesWithinAABB(EntityPlayerMP.class, boundingBox))
        {
            EntityPlayerMP player = (EntityPlayerMP)obj;
            if (targetFilter.canSendTo(player))
            {
                sendTo(new OpenUIPacket(ui), player);
                WorldSpaceUI.map.uiPlayers.put(ui.getUniqueId(), player);
            }
        }
    }

    public void sendUIClose(int uiUniqueIndex, ITargetFilter targetFilter)
    {
        EntityPlayerMP[] uiPlayers = WorldSpaceUI.map.uiPlayers.get(uiUniqueIndex).toArray(new EntityPlayerMP[0]);
        for (EntityPlayerMP player : uiPlayers)
        {
            if (targetFilter.canSendTo(player))
            {
                sendTo(new CloseUIPacket(uiUniqueIndex), player);
            }
            WorldSpaceUI.map.uiPlayers.remove(uiUniqueIndex, player);
        }
    }
}
