package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface ICraftingTableRecipe
{
    CraftingTableType getCraftingTableType();

    boolean isSupportTablesValid(Set<ISupportTable> supportTables);

    ItemStack getResultItem();
}
