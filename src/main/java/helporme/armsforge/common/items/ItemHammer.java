package helporme.armsforge.common.items;

import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.forge.wrapper.items.ItemToolBase;

public class ItemHammer extends ItemToolBase implements IHammer
{
    protected final HammerType hammerType;

    public ItemHammer(String name, HammerType hammerType, int maxDamage)
    {
        super(name);
        this.hammerType = hammerType;
        setMaxDamage(maxDamage);
    }

    public HammerType getHammerType()
    {
        return hammerType;
    }
}
