package helporme.armsforge.common.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public final class RecipesRegistry
{
    public static void addDefaultRecipes()
    {
        addMetalBlocks();
    }

    public static void addMetalBlocks()
    {
        for (int i = 0; i < 9; i++)
        {
            Block block = BlocksRegistry.getBlockByName("MetalBlock");
            Item ingot = ItemsRegistry.getItemByName("IngotBasic");

            ItemStack blockStack = new ItemStack(block, 1, i);
            ItemStack ingotStack = new ItemStack(ingot, 1, i);
            ItemStack ingotsStack = new ItemStack(ingot, 9, i);

            GameRegistry.addShapelessRecipe(blockStack, repeat(ingotStack, 9));
            GameRegistry.addShapelessRecipe(ingotsStack, blockStack);
        }
    }

    private static Object[] repeat(ItemStack stack, int times)
    {
        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        for (int i = 0; i < times; i++)
        {
            itemStacks.add(stack);
        }
        return itemStacks.toArray();
    }
}
