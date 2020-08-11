package helporme.armsforge.common.integration.armsforge;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tiles.CraftingTableType;
import helporme.armsforge.api.blocks.tiles.CraftingTableTypes;
import helporme.armsforge.common.core.Version;

public final class RecipeIcons
{
    public static void addAll()
    {
        addIconToTable("RecipeAnvil", CraftingTableTypes.masterAnvil);
        addIconToTable("RecipeAnvil", CraftingTableTypes.thaumAnvil);
        addIconToTable("RecipeAnvil", CraftingTableTypes.primalAnvil);
        addIconToTable("RecipeArmorer", CraftingTableTypes.armorerTable);
        addIconToTable("RecipeArmssmith", CraftingTableTypes.armssmithTable);
    }

    public static void addIconToTable(String recipeName, CraftingTableType tableType)
    {
        ArmsForgeApi.attachRecipeTextureToTable(Version.modid + ":recipes/" + recipeName, tableType);
    }
}
