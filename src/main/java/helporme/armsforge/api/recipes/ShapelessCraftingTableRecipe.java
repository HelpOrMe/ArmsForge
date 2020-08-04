package helporme.armsforge.api.recipes;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class ShapelessCraftingTableRecipe implements ICraftingTableRecipe
{
    protected final String name;
    protected final CraftingTableType craftingTableType;
    protected final ItemStack result;
    protected final ItemStack[] ingredients;

    public ShapelessCraftingTableRecipe(CraftingTableType craftingTableType, ItemStack result, ItemStack... ingredients)
    {
        this.name = result.getItem().getUnlocalizedName();
        this.craftingTableType = craftingTableType;
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public CraftingTableType getCraftingTableType()
    {
        return craftingTableType;
    }

    @Override
    public boolean isSupportTablesValid(Set<ISupportTable> supportTables)
    {
        for (ItemStack ingredient : ingredients)
        {
            if (!isTablesHasItem(supportTables, ingredient))
            {
                return false;
            }
        }
        return true;
    }

    protected boolean isTablesHasItem(Set<ISupportTable> supportTables, ItemStack itemStack)
    {
        for (ISupportTable supportTable : supportTables)
        {
            if (!supportTable.getItemOnTable().isItemEqual(itemStack))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getResultItem()
    {
        return result;
    }
}
