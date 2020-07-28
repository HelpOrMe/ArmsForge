package helporme.armsforge.api.items.hammer;

public class HammerType
{
    protected int size;
    protected int tir;
    protected int maxUses;

    public HammerType(int size, int tir, int maxUses)
    {
        this.size = size;
        this.tir = tir;
        this.maxUses = maxUses;
    }

    public int getSize()
    {
        return size;
    }

    public int getTir()
    {
        return tir;
    }

    public int getMaxUses()
    {
        return maxUses;
    }
}
