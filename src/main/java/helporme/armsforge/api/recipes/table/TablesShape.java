package helporme.armsforge.api.recipes.table;

import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablesShape
{
    protected ICraftingTable craftingTable;
    protected ISupportTable[] supportTables;

    protected Map<Vector3Int, ISupportTable> sortedSupportTables;
    protected Vector3Int min = Vector3Int.max;
    protected Vector3Int max = Vector3Int.min;

    public final Map<String, Character> itemChars = new HashMap<>();

    public  TablesShape(ICraftingTable craftingTable, ISupportTable[] supportTables)
    {
        this.craftingTable = craftingTable;
        this.supportTables = supportTables;
        this.sortedSupportTables = new HashMap<>();
        resortTablesByCoordinates();
    }

    public void resortTablesByCoordinates()
    {
        sortedSupportTables.clear();
        for (ISupportTable supportTable : supportTables)
        {
            Vector3Int offset = supportTable.getPosition().minus(craftingTable.getPosition());
            updateMin(offset);
            updateMax(offset);
            sortedSupportTables.put(offset, supportTable);
        }
    }

    protected void updateMin(Vector3Int position)
    {
        int minX = Integer.min(min.x, position.x);
        int minY = Integer.min(min.y, position.y);
        int minZ = Integer.min(min.z, position.z);
        min = new Vector3Int(minX, minY, minZ);
    }

    protected void updateMax(Vector3Int position)
    {
        int maxX = Integer.max(max.x, position.x);
        int maxY = Integer.max(max.y, position.y);
        int maxZ = Integer.max(max.z, position.z);
        max = new Vector3Int(maxX, maxY, maxZ);
    }

    public String[] parseTablesWithItems()
    {
        List<String> shape = new ArrayList<>();
        shape.add(getInfoStroke());
        for (int z = min.z; z < max.z + 1; z++)
        {
            StringBuilder row = new StringBuilder();
            for (int x = min.x; x < max.x + 1; x++)
            {
                StringBuilder symbol = new StringBuilder();
                for (int y = min.y; y < max.y + 1; y++)
                {
                    symbol.append(getItemCharBy(new Vector3Int(x, y, z)));
                }
                row.append(symbol);
            }
            shape.add(row.toString());
        }
        return shape.toArray(new String[0]);
    }

    public String getInfoStroke()
    {
        return "" + (max.y + 1);
    }

    public char getItemCharBy(Vector3Int position)
    {
        char chr = ' ';
        if (sortedSupportTables.containsKey(position))
        {
            ISupportTable table = sortedSupportTables.get(position);
            if (!table.isEmptyAt(0))
            {
                String convertedItemStack = ItemStackHelper.convertItemStackToString(table.getStackInSlot(0));
                updateItemSymbols(convertedItemStack);
                chr = itemChars.get(convertedItemStack);
            }
        }
        return chr;
    }

    public void updateItemSymbols(String... convertedItemStacks)
    {
        for (String convertedItemStack : convertedItemStacks)
        {
            if (!itemChars.containsKey(convertedItemStack))
            {
                char newSym = (char) (33 + itemChars.keySet().size());
                itemChars.put(convertedItemStack, newSym);
            }
        }
    }
}
