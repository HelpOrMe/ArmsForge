package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.items.IHammer;

import java.util.Set;

public interface ICraftingTable extends ITable
{
    Set<ISupportTable> getSupportTablesNear(int radius);

    CraftingTableType getTableType();

    void onHammerBlow(IHammer hammer);
}
