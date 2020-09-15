package helporme.worldspaceui.ui;

public enum UICallMode
{
    NONE,
    SERVER_OPEN,
    CLIENT_OPEN,
    SERVER_TICK,
    CLIENT_TICK,
    RENDER,
    SERVER_CLOSE,
    CLIENT_CLOSE;

    public boolean isServer()
    {
        switch (this)
        {
            case SERVER_OPEN:
            case SERVER_TICK:
            case SERVER_CLOSE:
               return true;
        }
        return false;
    }

    public boolean isClient()
    {
        switch (this)
        {
            case CLIENT_TICK:
            case CLIENT_OPEN:
            case RENDER:
            case CLIENT_CLOSE:
                return true;
        }
        return false;
    }
}
