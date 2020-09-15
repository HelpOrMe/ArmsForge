package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.WorldSpaceUIServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class UI
{
    protected final Logger logger = LogManager.getLogger();
    public int uniqueId;
    public UILocation location;

    public void onOpen() {}

    public void onUI() { }

    public void onClose() { }

    protected void close()
    {
        if (UILayout.getCurrentUI() == this)
        {
            if (UILayout.getCurrentMode().isServer())
            {
                WorldSpaceUIServer.closeUI(uniqueId);
                return;
            }
            if (UILayout.getCurrentMode().isClient())
            {
                WorldSpaceUI.closeUI(uniqueId);
                return;
            }
        }
        logger.error("UI.close() can be called only from UI class! Use `WorldSpaceUI|Server.closeUI()`");
    }
}
