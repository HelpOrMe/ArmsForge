package helporme.worldspaceui;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.ui.UI;
import helporme.worldspaceui.ui.UILocation;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;
import java.util.Map;

public class UIMap
{
    protected int lastUIid = -1;
    public Map<Integer, String> uiIdToUIClassName = new HashMap<>();
    public Map<String, Integer> uiClassToUIid = new HashMap<>();

    @SideOnly(Side.CLIENT)
    public Map<Integer, UI> renderPool = new HashMap<>();
    @SideOnly(Side.CLIENT)
    public Map<Integer, UI> clientUIUpdatePool = new HashMap<>();

    @SideOnly(Side.SERVER)
    public Multimap<UILocation, UI> uiLocations = HashMultimap.create();
    @SideOnly(Side.SERVER)
    public Multimap<Integer, EntityPlayerMP> uiPlayers = HashMultimap.create();
    @SideOnly(Side.SERVER)
    public Map<Integer, UI> serverUIUpdatePool = new HashMap<>();

    public void attachUIid(String UIClassName)
    {
        lastUIid++;
        uiIdToUIClassName.put(lastUIid, UIClassName);
        uiClassToUIid.put(UIClassName, lastUIid);
    }
}
