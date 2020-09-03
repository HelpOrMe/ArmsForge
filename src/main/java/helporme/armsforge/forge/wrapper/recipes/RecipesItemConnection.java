package helporme.armsforge.forge.wrapper.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;

public abstract class RecipesItemConnection implements IRecipe
{
    protected Class<? extends Item> majorItemType;
    protected Item minorItem;

    public RecipesItemConnection(Class<? extends Item> majorItemType, Item minorItem)
    {
        this.majorItemType = majorItemType;
        this.minorItem = minorItem;
    }

    public boolean isMultipleItemConnection()
    {
        return false;
    }

    @Override
    public boolean matches(InventoryCrafting inventory, World world)
    {
        ItemConnection itemConnection = getItemConnection(inventory);
        if (itemConnection.isValid() && (!isMultipleItemConnection() || itemConnection.minorStacks.size() == 1))
        {
            return matches(itemConnection);
        }
        return false;
    }

    public boolean matches(ItemConnection connection)
    {
        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory)
    {
        return getCraftingResult(getItemConnection(inventory));
    }

    public abstract ItemStack getCraftingResult(ItemConnection itemConnection);

    protected ItemConnection getItemConnection(InventoryCrafting inventory)
    {
        ItemStack majorStack = null;
        ArrayList<ItemStack> minorStacks = new ArrayList<>();

        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack stack = inventory.getStackInSlot(i);

            if (stack != null)
            {
                Item item = stack.getItem();
                if (majorItemType.isInstance(item) && majorStack == null)
                {
                    majorStack = stack;
                }
                else if (item == minorItem)
                {
                    minorStacks.add(stack);
                }
                else break;
            }
        }

        return new ItemConnection(majorStack, minorStacks);
    }

    @Override
    public int getRecipeSize() {
        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    protected static class ItemConnection
    {
        public ItemStack majorStack;
        public ArrayList<ItemStack> minorStacks;

        public ItemConnection(ItemStack mainItem, ArrayList<ItemStack> minorStacks)
        {
            this.majorStack = mainItem;
            this.minorStacks = minorStacks;
        }

        public boolean isValid()
        {
            return majorStack != null && minorStacks.size() > 0;
        }
    }
}
