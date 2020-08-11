package helporme.armsforge.api.blocks.tiles;

public class CraftingTableType
{
    public String name;
    public int tir;
    public int[] subTirs = new int[0];

    public CraftingTableType()
    {

    }

    public CraftingTableType(String name, int tir, int... subTirs)
    {
        this(name, tir);
        this.subTirs = subTirs;
    }

    public CraftingTableType(String name, int tir)
    {
        this.name = name;
        this.tir = tir;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof CraftingTableType)) return false;

        CraftingTableType tableType = (CraftingTableType)object;
        return nameEquals(tableType) && tirWithSubTirsEquals(tableType);
    }

    public boolean nameEquals(CraftingTableType tableType)
    {
        return name.equals(tableType.name);
    }

    public boolean tirWithSubTirsEquals(CraftingTableType tableType)
    {
        for (int subTir : subTirs)
        {
            if (subTir == tir)
            {
                return true;
            }
        }
        return tirEquals(tableType);
    }

    public boolean tirEquals(CraftingTableType tableType)
    {
        return tableType.tir == tir;
    }

    @Override
    public int hashCode()
    {
        int hash = name.hashCode();
        return ((hash << 5) + hash) ^ tir;
    }

    @Override
    public String toString()
    {
        return name + "@" + tir;
    }
}
