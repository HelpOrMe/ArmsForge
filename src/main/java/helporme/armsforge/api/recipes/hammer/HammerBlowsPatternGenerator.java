package helporme.armsforge.api.recipes.hammer;

import helporme.armsforge.api.ArmsForgeApi;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Random;

public class HammerBlowsPatternGenerator implements Iterator<HammerBlowPattern>
{
    protected final Random random = new Random();

    protected ItemStack[] ingredients;
    protected int ingredientIndex;

    public HammerBlowsPatternGenerator(ItemStack... ingredients)
    {
        this.ingredients = ingredients;
        ingredientIndex = ingredients.length;
    }

    @Override
    public boolean hasNext()
    {
        return ingredientIndex >= 0;
    }

    @Override
    public HammerBlowPattern next()
    {
        ingredientIndex--;
        return getNextBlow();
    }

    public HammerBlowPattern getNextBlow()
    {
        ItemStack ingredient = ingredients[ingredientIndex];
        HammerBlowPattern[] hammerBlowPatterns = ArmsForgeApi.getHammerBlowsPatternsForItem(ingredient);
        return hammerBlowPatterns[random.nextInt(hammerBlowPatterns.length)];
    }
}
