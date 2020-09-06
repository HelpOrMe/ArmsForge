package helporme.worldspaceui.ui;

public class UILocation
{
    public int chunkX;
    public int chunkZ;
    public int dimension;

    public UILocation(int chunkX, int chunkZ, int dimension)
    {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.dimension = dimension;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof UILocation)) return false;

        UILocation location = (UILocation)object;
        return chunkX == location.chunkX && chunkZ == location.chunkZ && dimension == location.dimension;
    }

    @Override
    public int hashCode()
    {
        return chunkX ^ chunkZ << 2 ^ dimension >> 2;
    }
}
