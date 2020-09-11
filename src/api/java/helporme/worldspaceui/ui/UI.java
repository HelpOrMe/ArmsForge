package helporme.worldspaceui.ui;

import helporme.worldspaceui.WorldSpaceUI;
import helporme.worldspaceui.WorldSpaceUIServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class UI
{
    protected final Logger logger = LogManager.getLogger();
    public int uniqueId;

    public void onUI() { }

    protected void sync()
    {
        if (UILayout.getCurrentUI() == this)
        {
            if (UILayout.getCurrentMode() == UICallMode.SERVER_TICK)
            {
                WorldSpaceUIServer.network.syncUI(this);
            }
            return;
        }
        logger.error("UI.sync() can be called only from onUI method! Use `WorldSpaceUIServer.network.syncUI()`");
    }

    protected void close()
    {
        if (UILayout.getCurrentUI() == this)
        {
            switch (UILayout.getCurrentMode())
            {
                case SERVER_TICK:
                    WorldSpaceUIServer.closeUI(uniqueId);
                    return;
                case CLIENT_TICK:
                case RENDER:
                    WorldSpaceUI.closeUI(uniqueId);
                    return;
            }
        }
        logger.error("UI.close() can be called only from onUI method! Use `WorldSpaceUI|Server.closeUI()`");
    }
}
