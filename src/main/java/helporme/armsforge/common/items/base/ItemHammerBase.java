package helporme.armsforge.common.items.base;

import helporme.armsforge.api.items.hammer.HammerType;
import helporme.armsforge.api.items.hammer.IHammer;

public class ItemHammerBase extends ItemToolBase implements IHammer
{
    protected HammerType hammerType;

    public ItemHammerBase(String name, HammerType hammerType)
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
