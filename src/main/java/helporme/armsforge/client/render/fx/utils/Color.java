package helporme.armsforge.client.render.fx.utils;

public class Color
{
    public final float r;
    public final float g;
    public final float b;

    public Color(int r, int g, int b)
    {
        this(r / 255f, g / 255f, b / 255f);
    }

    public Color(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Color parse(int hex)
    {
        return new Color(
                (hex & 0xFF0000) >> 16,
                (hex & 0xFF00) >> 8,
                (hex & 0xFF));
    }
}
