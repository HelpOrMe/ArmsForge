package helporme.armsforge.api.items;

public class HammerType
{
    public final int size;
    public final int tir;

    public HammerType(int size, int tir)
    {
        this.size = size;
        this.tir = tir;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof HammerType)) return false;

        HammerType hammerType = (HammerType)object;
        return size == hammerType.size && tir == hammerType.size;
    }

    @Override
    public int hashCode()
    {
        return ((size << 5) + size) ^ tir;
    }
}
