package helporme.armsforge.api.items;

import net.minecraft.nbt.NBTTagCompound;

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
        return size == hammerType.size && tir == hammerType.tir;
    }

    @Override
    public int hashCode()
    {
        return ((size << 5) + size) ^ tir;
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setInteger("tir", tir);
        nbtTagCompound.setInteger("size", size);
    }

    public static HammerType fromNBT(NBTTagCompound nbtTagCompound)
    {
        int size = nbtTagCompound.getInteger("size");
        int tir = nbtTagCompound.getInteger("tir");
        return new HammerType(size, tir);
    }
}
