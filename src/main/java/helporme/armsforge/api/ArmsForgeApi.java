package helporme.armsforge.api;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.recipes.hammer.HammerBlowPattern;
import helporme.armsforge.api.recipes.hammer.HammerTypes;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.ShapelessCraftingTableRecipe;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public final class ArmsForgeApi
{
    private static final Map<HammerType, Item> hammerItems = new HashMap<>();
    private static final Map<String, HammerBlowPattern[]> hammerBlowsPatternsForItems = new HashMap<>();

    private static final Map<CraftingTableType, Set<String>> recipeNamesByTables = new HashMap<>();
    private static final Map<String, ICraftingTableRecipe> recipes = new HashMap<>();
    private static final Map<CraftingTableType, String> recipeIconForTable = new HashMap<>();

    public static void addHammer(Item hammerItem, HammerType hammerType)
    {
        hammerItems.put(hammerType, hammerItem);
    }

    public static Item getHammerItem(HammerType hammerType)
    {
        return hammerItems.get(hammerType);
    }

    public static void attachHammerBlowsPatternsToItem(ItemStack itemStack, HammerBlowPattern... hammerBlowPattern)
    {
        hammerBlowsPatternsForItems.put(ItemStackHelper.convertItemStackToString(itemStack), hammerBlowPattern);
    }

    public static HammerBlowPattern[] getHammerBlowsPatternsForItem(ItemStack itemStack)
    {
        String itemString = ItemStackHelper.convertItemStackToString(itemStack);
        if (hammerBlowsPatternsForItems.containsKey(itemString))
        {
            return hammerBlowsPatternsForItems.get(itemString);
        }
        return new HammerBlowPattern[] { new HammerBlowPattern(HammerTypes.mediumIron) };
    }

    public static void addCraftingTableRecipes(CraftingTableType craftingTableType,
                                               ItemStack result, ItemStack mainItem, ItemStack... ingredients)
    {
        addCraftingTableRecipes(new ShapelessCraftingTableRecipe(craftingTableType, result, mainItem, ingredients));
    }

    public static void addCraftingTableRecipes(ICraftingTableRecipe... newRecipes)
    {
        for (ICraftingTableRecipe newRecipe : newRecipes)
        {
            addCraftingRecipe(newRecipe);

            CraftingTableType tableType = newRecipe.getCraftingTableType();
            if (!recipeNamesByTables.containsKey(tableType))
            {
                recipeNamesByTables.put(tableType, new HashSet<>());
            }
            recipeNamesByTables.get(tableType).add(newRecipe.getName());
        }
    }

    private static void addCraftingRecipe(ICraftingTableRecipe recipe)
    {
        recipes.put(recipe.getName(), recipe);
    }

    public static ICraftingTableRecipe[] getCraftingTableRecipesFor(CraftingTableType tableType)
    {
        Set<String> recipeNames = recipeNamesByTables.get(tableType);
        Set<ICraftingTableRecipe> recipes = new HashSet<>();
        for (String recipeName : recipeNames)
        {
            recipes.add(getRecipeByName(recipeName));
        }
        return recipes.toArray(new ICraftingTableRecipe[0]);
    }

    public static ICraftingTableRecipe[] getAllCraftingTableRecipes()
    {
        return recipes.values().toArray(new ICraftingTableRecipe[0]);
    }

    public static ICraftingTableRecipe getRecipeByName(String name)
    {
        return recipes.get(name);
    }

    public static void addRecipeIconNameToTable(String iconTextureName, CraftingTableType tableType)
    {
        recipeIconForTable.put(tableType, iconTextureName);
    }

    public static String getRecipeIconName(CraftingTableType craftingTableType)
    {
        return recipeIconForTable.get(craftingTableType);
    }

    public static CraftingTableType[] getCraftingTableTypes()
    {
        return recipeNamesByTables.keySet().toArray(new CraftingTableType[0]);
    }
}
