package helporme.armsforge.api.items;

import net.minecraft.nbt.NBTTagCompound;

public class HammerType
{
    public final int size;
    public final int tier;

    public HammerType(int size, int tier)
    {
        this.size = size;
        this.tier = tier;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof HammerType)) return false;

        HammerType hammerType = (HammerType)object;
        return size == hammerType.size && tier == hammerType.tier;
    }

    @Override
    public int hashCode()
    {
        return ((size << 5) + size) ^ tier;
    }

    @Override
    public String toString()
    {
        return "(" + size + ", " + tier + ")";
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setInteger("tier", tier);
        nbtTagCompound.setInteger("size", size);
    }

    public static HammerType fromNBT(NBTTagCompound nbtTagCompound)
    {
        int size = nbtTagCompound.getInteger("size");
        int tier = nbtTagCompound.getInteger("tier");
        return new HammerType(size, tier);
    }
}
