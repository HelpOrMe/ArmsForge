package helporme.armsforge.forge.wrapper.recipes;

import helporme.armsforge.forge.wrapper.items.base.ItemArmorDyed;
import helporme.armsforge.forge.wrapper.utils.Color;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

public class RecipesArmorDyed extends RecipesItemConnection
{
    public RecipesArmorDyed()
    {
        super(ItemArmorDyed.class, Items.dye);
    }

    @Override
    public ItemStack getCraftingResult(ItemConnection itemConnection)
    {
        ItemStack armorStack = itemConnection.majorStack;
        ItemArmorDyed armorItem = (ItemArmorDyed)armorStack.getItem();

        Color color = getColorFromItemConnection(itemConnection);
        ItemStack armorStackCopy = armorStack.copy();

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("display", new NBTTagCompound());
        armorStackCopy.setTagCompound(tagCompound);

        armorItem.setColor(armorStackCopy, color.parseToHex());
        return armorStackCopy;
    }

    protected Color getColorFromItemConnection(ItemConnection itemConnection)
    {
        ItemStack armorStack = itemConnection.majorStack;
        ItemArmorDyed armorItem = (ItemArmorDyed)armorStack.getItem();
        ArrayList<ItemStack> dyes = itemConnection.minorStacks;

        if (dyes.size() > 1)
        {
            Color color = Color.parseFromHex(armorItem.getColorFromItemStack(armorStack, 0));
            for (ItemStack dye : dyes)
            {
                color.add(getColorFromDye(dye));
            }
            color.divide(dyes.size() + 1);
            return color;
        }
        return getColorFromDye(dyes.get(0));
    }

    protected Color getColorFromDye(ItemStack dye)
    {
        // ref: net/minecraft/item/crafting/RecipesArmorDyes.java
        float[] colorList = EntitySheep.fleeceColorTable[BlockColored.func_150032_b(dye.getItemDamage())];
        return new Color(colorList[0], colorList[1], colorList[2]);
    }
}
