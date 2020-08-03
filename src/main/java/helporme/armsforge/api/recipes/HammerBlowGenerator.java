package helporme.armsforge.api.recipes;

import helporme.armsforge.api.ArmsForgeApi;
import helporme.armsforge.api.items.IHammer;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Random;

public class HammerBlowGenerator implements Iterator<IHammer>
{
    protected final Random random = new Random();

    protected ItemStack[] ingredients;
    protected int ingredientIndex;

    public HammerBlowGenerator(ItemStack... ingredients)
    {
        this.ingredients = ingredients;
        ingredientIndex = ingredients.length;
    }

    @Override
    public boolean hasNext()
    {
        return ingredientIndex > 0;
    }

    @Override
    public IHammer next()
    {
        ingredientIndex--;
        return getNextHammer();
    }

    public IHammer getNextHammer()
    {
        ItemStack ingredient = ingredients[ingredientIndex];
        IHammer[] hammers = ArmsForgeApi.getHammersForItem(ingredient);
        return hammers[random.nextInt(hammers.length)];
    }
}
