package helporme.armsforge.common.registry.utils;

public final class Colors
{
    public static final int holyColor = 0xFFFFAF;
    public static final int crimsonColor = 0x821C29;
    public static final int damnedColor = 0x960000;
    public static final int endColor = 0x007F56;
    public static final int mithrilColor = 0xD7FFFA;
    public static final int gnomeColor = 0xFF9C00;
    public static final int bronzeColor = 0xFFCB6B;
    public static final int steelColor = 0x919191;
    public static final int goldColor = 0xD2C81E;
    public static final int carbonColor = 0x646464;
    public static final int ironColor = 0x7D7D7D;

    public static final int[] allColors = new int[]
            {
                    holyColor, crimsonColor, damnedColor,
                    endColor, mithrilColor, gnomeColor,
                    bronzeColor, steelColor, goldColor,
                    carbonColor, ironColor
            };

    public static final int[] metalColors = new int[]
            {
                    holyColor, crimsonColor, damnedColor,
                    endColor, mithrilColor, gnomeColor,
                    bronzeColor, steelColor, carbonColor
            };

    public static final int[] chainColors = new int[]
            {
                    crimsonColor, endColor, mithrilColor,
                    bronzeColor, goldColor, ironColor
            };

    public static final int[] rivetColors = new int[]
            {
                    gnomeColor, bronzeColor, goldColor,
                    ironColor
            };

    public static final int[] plateColors = new int[]
            {
                    holyColor, crimsonColor, damnedColor,
                    bronzeColor, steelColor, carbonColor,
                    ironColor
            };

    public static final int[] wireColors = new int[]
            {
                    endColor, goldColor, carbonColor,
                    ironColor
            };
}
