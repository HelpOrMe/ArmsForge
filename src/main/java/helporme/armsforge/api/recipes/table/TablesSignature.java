package helporme.armsforge.api.recipes.table;

import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class TablesSignature
{
    protected final ICraftingTable craftingTable;
    protected final ISupportTable[] supportTables;

    protected ItemStack[] itemStacksOnSupportTables;
    protected ItemStack itemStackOnCraftingTable;

    public TablesSignature(ICraftingTable craftingTable, ISupportTable[] supportTables)
    {
        this.craftingTable = craftingTable;
        this.supportTables = supportTables;
        updateItemStacks();
    }

    public void updateItemStacks()
    {
        Set<ItemStack> itemStacks = new HashSet<>();
        for (ISupportTable supportTable : supportTables)
        {
            if (!supportTable.isEmptyAt(0))
            {
                itemStacks.add(supportTable.getStackInSlot(0));
            }
        }
        itemStacksOnSupportTables = itemStacks.toArray(new ItemStack[0]);
        itemStackOnCraftingTable = craftingTable.getStackInSlot(0);
    }

    public ItemStack[] getItemsOnSupportTables()
    {
        return itemStacksOnSupportTables;
    }

    public ItemStack getItemOnCraftingTable()
    {
        return itemStackOnCraftingTable;
    }

    public boolean hasItemOnCraftingTable()
    {
        return itemStackOnCraftingTable != null;
    }

    public boolean containsItem(ItemStack itemStack)
    {
        for (ISupportTable supportTable : supportTables)
        {
            ItemStack itemOnTable = supportTable.getStackInSlot(0);
            boolean tableHasItem = supportTable.isEmptyAt(0);
            boolean itemStacksEquals = itemOnTable.isItemEqual(itemStack);

            if (tableHasItem && itemStacksEquals && itemOnTable.stackSize >= itemStack.stackSize)
            {
                return true;
            }
        }
        return false;
    }

    public TablesShape getShape()
    {
        return new TablesShape(craftingTable, supportTables);
    }
}
