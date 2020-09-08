package helporme.worldspaceui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.worldspaceui.ui.UI;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class UIMapClient extends UIMap
{
    public Map<Integer, UI> renderPool = new HashMap<>();
    public Map<Integer, UI> uiUpdatePool = new HashMap<>();
}
