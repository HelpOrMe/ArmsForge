package helporme.armsforge.forge.wrapper.recipes;

import helporme.armsforge.forge.wrapper.items.base.ItemArmorDyed;
import helporme.armsforge.forge.wrapper.utils.Color;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.HashSet;

public class RecipesArmorDyed implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventory, World world)
    {
        ArmorWithDyes armorWithDyes = getArmorAndDyesFromInventory(inventory);
        return armorWithDyes.armorStack != null && armorWithDyes.dyes.size() > 0;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventory)
    {
        ArmorWithDyes armorWithDyes = getArmorAndDyesFromInventory(inventory);
        ItemStack armorStack = armorWithDyes.armorStack;
        HashSet<ItemStack> dyes = armorWithDyes.dyes;

        Color middleColor = new Color();
        for (ItemStack dye : dyes)
        {
            middleColor.add(getColorFromDye(dye));
        }
        middleColor.divide(dyes.size());

        ItemArmorDyed armorItem = (ItemArmorDyed)armorStack.getItem();
        armorItem.setColor(armorStack, middleColor.parseToHex());
        return armorStack;
    }

    protected ArmorWithDyes getArmorAndDyesFromInventory(InventoryCrafting inventory)
    {
        ItemStack armorStack = null;
        HashSet<ItemStack> dyes = new HashSet<>();

        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack stack = inventory.getStackInSlot(i);

            if (stack != null)
            {
                Item item = stack.getItem();
                if (item instanceof ItemArmorDyed && armorStack == null)
                {
                    armorStack = stack;
                }
                else if (item == Items.dye)
                {
                    dyes.add(stack);
                }
                else
                {
                    return null;
                }
            }
        }

        return new ArmorWithDyes(armorStack, dyes);
    }

    protected Color getColorFromDye(ItemStack dye)
    {
        // ref: net/minecraft/item/crafting/RecipesArmorDyes.java
        float[] colorList = EntitySheep.fleeceColorTable[BlockColored.func_150032_b(dye.getItemDamage())];
        return new Color(colorList[0], colorList[1], colorList[2]);
    }

    @Override
    public int getRecipeSize() {
        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    protected class ArmorWithDyes
    {
        public ItemStack armorStack;
        public HashSet<ItemStack> dyes;

        public ArmorWithDyes(ItemStack armorStack, HashSet<ItemStack> dyes)
        {
            this.armorStack = armorStack;
            this.dyes = dyes;
        }
    }
}
