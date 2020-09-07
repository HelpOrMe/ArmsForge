package helporme.worldspaceui.types;

import net.minecraft.util.MathHelper;

import java.util.Random;

public class Vector3f
{
    public float x;
    public float y;
    public float z;

    public Vector3f(double x, double y, double z)
    {
        this((float)x, (float)y, (float)z);
    }

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f lerp(Vector3f a, Vector3f b, float t)
    {
        return a.add(b.minus(a).multiply(t));
    }

    public static float distance(Vector3f a, Vector3f b)
    {
        return a.minus(b).sqrMagnitude();
    }

    public static Vector3f getNextRandomOffset(Random random, float max)
    {
        float x = random.nextFloat() % max * (random.nextInt(2) == 0 ? -1 : 1);
        float y = random.nextFloat() % max;
        float z = random.nextFloat() % max * (random.nextInt(2) == 0 ? -1 : 1);
        return new Vector3f(x, y, z);
    }

    public static Vector3f min(Vector3f a, Vector3f b)
    {
        return new Vector3f(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z));
    }

    public static Vector3f max(Vector3f a, Vector3f b)
    {
        return new Vector3f(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z));
    }

    public float sqrMagnitude()
    {
        return MathHelper.sqrt_float(x * x + y * y + z * z);
    }

    public Vector3f divide(float value)
    {
        return new Vector3f(x / value, y / value, z / value);
    }

    public Vector3f multiply(float value)
    {
        return new Vector3f(x * value, y * value, z * value);
    }

    public Vector3f minus(Vector3f vector)
    {
        return new Vector3f(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3f add(Vector3f vector)
    {
        return new Vector3f(x + vector.x, y + vector.y, z + vector.z);
    }

    public String toString()
    {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof Vector3f)) return false;

        Vector3f vector = (Vector3f)object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    @Override
    public int hashCode()
    {
        return Float.hashCode(x) ^ Float.hashCode(y) << 2 ^ Float.hashCode(z) >> 2;
    }
}
