package helporme.armsforge.common.items;

import helporme.armsforge.api.items.hammer.HammerType;
import helporme.armsforge.api.items.hammer.IHammer;
import helporme.armsforge.forge.wrapper.items.ItemToolBase;

public class ItemHammer extends ItemToolBase implements IHammer
{
    protected HammerType hammerType;

    public ItemHammer(String name, HammerType hammerType)
    {
        super(name);
        this.hammerType = hammerType;
        setMaxDamage(hammerType.getMaxUses());
    }

    public HammerType getHammerType()
    {
        return hammerType;
    }
}
