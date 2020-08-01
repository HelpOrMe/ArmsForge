package helporme.armsforge.api.blocks;

import java.util.List;

public interface ICraftingTable extends ITable
{
    List<ISupportTable> getSupportTablesNear(int radius);
}
