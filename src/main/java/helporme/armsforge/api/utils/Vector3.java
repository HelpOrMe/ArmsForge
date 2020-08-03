package helporme.armsforge.api.utils;

import net.minecraft.util.MathHelper;

public class Vector3
{
    public final float x;
    public final float y;
    public final float z;

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z ;
    }

    public static Vector3 lerp(Vector3 a, Vector3 b, float t)
    {
        return a.add(b.minus(a).multiply(t));
    }

    public static float distance(Vector3 a, Vector3 b)
    {
        return a.minus(b).sqrMagnitude();
    }

    public float sqrMagnitude()
    {
        return MathHelper.sqrt_float(x * x + y * y + z * z);
    }

    public Vector3 divide(float value)
    {
        return new Vector3(x / value, y / value, z / value);
    }

    public Vector3 multiply(float value)
    {
        return new Vector3(x * value, y * value, z * value);
    }

    public Vector3 minus(Vector3 vector)
    {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3 add(Vector3 vector)
    {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
