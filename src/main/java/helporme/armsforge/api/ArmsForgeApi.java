package helporme.armsforge.api;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.recipes.HammerTypes;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.ShapelessCraftingTableRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public final class ArmsForgeApi
{
    private static final Map<HammerType, Item> hammerItems = new HashMap<>();
    private static final Map<String, IHammer[]> specialHammersForItems = new HashMap<>();

    private static final Map<CraftingTableType, Set<ICraftingTableRecipe>> recipes = new HashMap<>();
    private static final Map<CraftingTableType, String> recipeIconForTable = new HashMap<>();

    public static void addHammer(Item hammerItem, HammerType hammerType)
    {
        hammerItems.put(hammerType, hammerItem);
    }

    public static Item getHammerItem(HammerType hammerType)
    {
        return hammerItems.get(hammerType);
    }

    public static void attachSpecialHammersToItem(ItemStack itemStack, IHammer... hammer)
    {
        specialHammersForItems.put(convertItemToString(itemStack), hammer);
    }

    public static IHammer[] getHammersForItem(ItemStack itemStack)
    {
        String itemString = convertItemToString(itemStack);
        if (specialHammersForItems.containsKey(itemString))
        {
            return specialHammersForItems.get(itemString);
        }
        return new IHammer[] { (IHammer)hammerItems.get(HammerTypes.mediumIron) };
    }

    private static String convertItemToString(ItemStack itemStack)
    {
        return Item.itemRegistry.getNameForObject(itemStack.getItem()) + ":" + itemStack.getItemDamage();
    }

    public static void addCraftingTableRecipes(CraftingTableType craftingTableType, ItemStack result, ItemStack... ingredients)
    {
        addCraftingTableRecipes(new ShapelessCraftingTableRecipe(craftingTableType, result, ingredients));
    }

    public static void addCraftingTableRecipes(ICraftingTableRecipe... newRecipes)
    {
        for (ICraftingTableRecipe newRecipe : newRecipes)
        {
            CraftingTableType tableType = newRecipe.getCraftingTableType();
            if (!recipes.containsKey(tableType))
            {
                recipes.put(tableType, new HashSet<>());
            }
            recipes.get(tableType).add(newRecipe);
        }
    }

    public static Set<ICraftingTableRecipe> getRecipesForCraftingTable(CraftingTableType tableType)
    {
        return recipes.get(tableType);
    }

    public static void addRecipeIconNameToTable(String iconTextureName, CraftingTableType tableType)
    {
        recipeIconForTable.put(tableType, iconTextureName);
    }

    public static String getRecipeIconName(CraftingTableType tableType)
    {
        return recipeIconForTable.get(tableType);
    }
}
