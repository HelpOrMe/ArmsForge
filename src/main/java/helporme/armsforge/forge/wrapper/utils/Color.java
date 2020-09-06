package helporme.armsforge.forge.wrapper.utils;

public class Color
{
    public float r;
    public float g;
    public float b;

    public Color() { }

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

    public static Color parseFromHex(int hex)
    {
        return new Color(
                (hex & 0xFF0000) >> 16,
                (hex & 0xFF00) >> 8,
                (hex & 0xFF));
    }

    public int parseToHex()
    {
        return (((int)(r * 255) & 0xff) << 16) | (((int)(g * 255) & 0xff) << 8) | ((int)(b * 255) & 0xff);
    }

    public void add(Color color)
    {
        r += color.r;
        g += color.g;
        b += color.b;
    }

    public void min(Color color)
    {
        r -= color.r;
        g -= color.g;
        b -= color.b;
    }

    public void divide(float n)
    {
        r = r / n;
        g = g / n;
        b = b / n;
    }
}
