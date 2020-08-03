package helporme.armsforge.api.recipes;

import helporme.armsforge.api.items.HammerType;

public class HammerBlowPattern
{
    public final HammerType hammerType;
    public final float chance;
    public final float timeForBlow;

    public HammerBlowPattern(HammerType hammerType, float chance, float timeForBlow)
    {
        this.hammerType = hammerType;
        this.chance = chance;
        this.timeForBlow = timeForBlow;
    }
}
