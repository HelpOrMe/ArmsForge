package helporme.armsforge.api;

import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.recipes.hammer.HammerBlowPattern;
import helporme.armsforge.api.recipes.hammer.HammerTypes;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.ShapelessCraftingTableRecipe;
import helporme.armsforge.forge.wrapper.utils.ItemStackHelper;
import net.minecraft.item.ItemStack;

import java.util.*;

public final class ArmsForgeApi
{
    private static final Map<HammerType, String> hammerTextureStrings = new HashMap<>();

    private static final Map<String, List<HammerBlowPattern>> hammerBlowsPatternsForItems = new HashMap<>();

    private static final Map<CraftingTableType, Set<String>> recipeNamesByTables = new HashMap<>();
    private static final Map<String, ICraftingTableRecipe> recipes = new HashMap<>();
    private static final Map<CraftingTableType, String> recipeTextureStringForTable = new HashMap<>();

    /**
     * Attach a texture to the hammer type. Used in the hammer icon render over the crafting table
     * @param hammerType Target hammer type. All hammers with size & tier 0 > 3 texture are already attached
     * @param textureName Texture string. Example: "armsforge:items/tools/BighammerIron.png
     */
    public static void attachHammerTexture(HammerType hammerType, String textureName)
    {
        if (hammerTextureStrings.containsKey(hammerType))
        {
            throw new IllegalArgumentException("Texture string for " + hammerType + " already attached");
        }
        hammerTextureStrings.put(hammerType, textureName);
    }

    public static boolean hasHammerTexture(HammerType hammerType)
    {
        return hammerTextureStrings.containsKey(hammerType);
    }

    public static String getHammerTexture(HammerType hammerType)
    {
        if (!hammerTextureStrings.containsKey(hammerType))
        {
            throw new IllegalArgumentException("Texture string for " + hammerType + " does not exists");
        }
        return hammerTextureStrings.get(hammerType);
    }

    /**
     * Add special hammer blows patterns to an item (count ignored).
     * Crafting tables (Master anvil, armorer table, etc.) will use one of the listed patterns for the item.
     */
    public static void addHammerBlowsPatternsToItem(ItemStack itemStack, HammerBlowPattern... hammerBlowPattern)
    {
        String itemString = ItemStackHelper.convertItemToString(itemStack);
        if (!hammerBlowsPatternsForItems.containsKey(itemString))
        {
            hammerBlowsPatternsForItems.put(itemString, new ArrayList<>());
        }
        List<HammerBlowPattern> patterns = hammerBlowsPatternsForItems.get(itemString);
        patterns.addAll(Arrays.asList(hammerBlowPattern));
    }

    public static void resetHammerBlowsPatternsForItem(ItemStack itemStack)
    {
        String itemString = ItemStackHelper.convertItemToString(itemStack);
        if (hammerBlowsPatternsForItems.containsKey(itemString))
        {
            List<HammerBlowPattern> patterns = hammerBlowsPatternsForItems.get(itemString);
            patterns.clear();
        }
    }

    /**
     * @return hammer blow pattern for medium iron hammer type by default
     */
    public static HammerBlowPattern[] getHammerBlowsPatternsForItem(ItemStack itemStack)
    {
        String itemString = ItemStackHelper.convertItemToString(itemStack);
        if (hammerBlowsPatternsForItems.containsKey(itemString))
        {
            return hammerBlowsPatternsForItems.get(itemString).toArray(new HammerBlowPattern[0]);
        }
        return new HammerBlowPattern[] { new HammerBlowPattern(HammerTypes.mediumIron) };
    }

    /**
     * Add shapeless crafting recipe for target table
     * @param craftingTableType You can get default table types from CraftingTableTypes
     */
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

    public static ICraftingTableRecipe[] getAllRecipesForTable(CraftingTableType tableType)
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

    /**
     * Get all added crafting table types from existed recipes
     */
    public static CraftingTableType[] getCraftingTableTypes()
    {
        return recipeNamesByTables.keySet().toArray(new CraftingTableType[0]);
    }

    /**
     * Attach recipe texture string to table type
     * All recipe textures for table types in CraftingTableTypes are already attached
     */
    public static void attachRecipeTextureToTable(String textureName, CraftingTableType tableType)
    {
        if (recipeTextureStringForTable.containsKey(tableType))
        {
            throw new IllegalArgumentException("Recipe texture string for " + tableType + " already attached");
        }
        recipeTextureStringForTable.put(tableType, textureName);
    }

    public static String getTableRecipeTextureString(CraftingTableType tableType)
    {
        if (!recipeTextureStringForTable.containsKey(tableType))
        {
            throw new IllegalArgumentException("Recipe texture string for " + tableType + " does not exists");
        }
        return recipeTextureStringForTable.get(tableType);
    }
}
