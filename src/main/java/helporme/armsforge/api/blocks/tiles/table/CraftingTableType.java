package helporme.armsforge.api.blocks.tiles.table;

public class CraftingTableType
{
    public String name;
    public int tier;
    public int[] subtiers = new int[0];

    public CraftingTableType()
    {

    }

    public CraftingTableType(String name, int tier, int... subtiers)
    {
        this(name, tier);
        this.subtiers = subtiers;
    }

    public CraftingTableType(String name, int tier)
    {
        this.name = name;
        this.tier = tier;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof CraftingTableType)) return false;

        CraftingTableType tableType = (CraftingTableType)object;
        return nameEquals(tableType) && tierWithSubtiersEquals(tableType);
    }

    public boolean nameEquals(CraftingTableType tableType)
    {
        return name.equals(tableType.name);
    }

    public boolean tierWithSubtiersEquals(CraftingTableType tableType)
    {
        for (int subtier : subtiers)
        {
            if (subtier == tier)
            {
                return true;
            }
        }
        return tierEquals(tableType);
    }

    public boolean tierEquals(CraftingTableType tableType)
    {
        return tableType.tier == tier;
    }

    @Override
    public int hashCode()
    {
        int hash = name.hashCode();
        return ((hash << 5) + hash) ^ tier;
    }

    @Override
    public String toString()
    {
        return name + "@" + tier;
    }
}
