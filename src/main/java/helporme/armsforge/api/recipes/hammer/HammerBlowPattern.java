package helporme.armsforge.api.recipes.hammer;

import helporme.armsforge.api.items.HammerType;

public class HammerBlowPattern
{
    public HammerType hammerType;
    public float time;

    public HammerBlowPattern(HammerType hammerType)
    {
        this(hammerType, 2.5f);
    }

    public HammerBlowPattern(HammerType hammerType, float time)
    {
        this.hammerType = hammerType;
        this.time = time;
    }
}
