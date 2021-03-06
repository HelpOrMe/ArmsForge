package helporme.armsforge.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.blocks.tiles.table.CraftingTableType;
import helporme.armsforge.api.items.IItemRecipe;
import helporme.armsforge.api.recipes.EmptyCraftingTableRecipe;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.forge.wrapper.items.base.ItemBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemRecipe extends ItemBase implements IItemRecipe
{
    protected IIcon[] icons;
    protected int[] iconRanges;

    public ItemRecipe()
    {
        super("Recipe", "recipes");
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        super.registerIcons(register);
        CraftingTableType[] craftingTableTypes = ArmsForgeApi.getCraftingTableTypes();

        icons = new IIcon[craftingTableTypes.length];
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = register.registerIcon(ArmsForgeApi.getTableRecipeTextureString(craftingTableTypes[i]));
        }

        recalculateIconRanges(craftingTableTypes);
    }


    protected void recalculateIconRanges(CraftingTableType[] craftingTableTypes)
    {
        iconRanges = new int[craftingTableTypes.length];

        for (int i = 0; i < iconRanges.length; i++)
        {
            iconRanges[i] = ArmsForgeApi.getAllRecipesForTable(craftingTableTypes[i]).length;
        }

        for (int i = 1; i < iconRanges.length; i++)
        {
            iconRanges[i] += iconRanges[i - 1];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        for (int i = 0; i < iconRanges.length; i++)
        {
            if (damage < iconRanges[i])
            {
                return icons[i];
            }
        }
        return itemIcon;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return getUnlocalizedName() + "_" + getRecipe(itemStack).getName();
    }

    @Override
    public ICraftingTableRecipe getRecipe(ItemStack itemStack)
    {
        int damage = itemStack.getItemDamage();
        ICraftingTableRecipe[] recipes = ArmsForgeApi.getAllCraftingTableRecipes();
        if (damage < recipes.length)
        {
            return recipes[damage];
        }
        return new EmptyCraftingTableRecipe();
    }
}
