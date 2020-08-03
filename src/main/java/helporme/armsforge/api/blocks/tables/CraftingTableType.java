package helporme.armsforge.api.blocks.tables;

public class CraftingTableType
{
    public String tableName;
    public int tir;
    public int[] subTirs = new int[0];

    public CraftingTableType(String tableName, int tir, int... subTirs)
    {
        this(tableName, tir);
        this.subTirs = subTirs;
    }

    public CraftingTableType(String tableName, int tir)
    {
        this.tableName = tableName;
        this.tir = tir;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof CraftingTableType)) return false;

        CraftingTableType tableType = (CraftingTableType)object;
        return nameEquals(tableType) && tirEquals(tableType);
    }

    @Override
    public int hashCode()
    {
        int hash = tableName.hashCode();
        return ((hash << 5) + hash) ^ tir;
    }

    public boolean nameEquals(CraftingTableType tableType)
    {
        return tableName.equals(tableType.tableName);
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
}
