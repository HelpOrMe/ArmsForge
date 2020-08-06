package helporme.armsforge.api.utils;

import net.minecraft.util.MathHelper;

public class Vector3
{
    public float x;
    public float y;
    public float z;

    public Vector3(double x, double y, double z)
    {
        this((float)x, (float)y, (float)z);
    }

    public Vector3(Vector3Int vectorInt)
    {
        this(vectorInt.x, vectorInt.y, vectorInt.z);
    }

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 lerp(Vector3 a, Vector3 b, float t)
    {
        return a.add(b.minus(a).multiply(t));
    }

    public static float distance(Vector3 a, Vector3 b)
    {
        return a.minus(b).sqrMagnitude();
    }

    public static Vector3 min(Vector3 a, Vector3 b)
    {
        return new Vector3(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z));
    }

    public static Vector3 max(Vector3 a, Vector3 b)
    {
        return new Vector3(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z));
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
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof Vector3)) return false;

        Vector3 vector = (Vector3)object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    @Override
    public int hashCode()
    {
        return Float.hashCode(x) ^ Float.hashCode(y) << 2 ^ Float.hashCode(z) >> 2;
    }
}
