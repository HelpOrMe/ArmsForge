package helporme.worldspaceui.types;

import net.minecraft.util.MathHelper;

import java.util.Random;

public class Vector3d
{
    public double x;
    public double y;
    public double z;

    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3d lerp(Vector3d a, Vector3d b, double t)
    {
        return a.add(b.minus(a).multiply(t));
    }

    public static double distance(Vector3d a, Vector3d b)
    {
        return a.minus(b).sqrMagnitude();
    }

    public static Vector3d getNextRandomOffset(Random random, double max)
    {
        double x = random.nextDouble() % max * (random.nextInt(2) == 0 ? -1 : 1);
        double y = random.nextDouble() % max;
        double z = random.nextDouble() % max * (random.nextInt(2) == 0 ? -1 : 1);
        return new Vector3d(x, y, z);
    }

    public static Vector3d min(Vector3d a, Vector3d b)
    {
        return new Vector3d(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z));
    }

    public static Vector3d max(Vector3d a, Vector3d b)
    {
        return new Vector3d(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z));
    }

    public double sqrMagnitude()
    {
        return MathHelper.sqrt_double(x * x + y * y + z * z);
    }

    public Vector3d divide(double value)
    {
        return new Vector3d(x / value, y / value, z / value);
    }

    public Vector3d multiply(double value)
    {
        return new Vector3d(x * value, y * value, z * value);
    }

    public Vector3d minus(Vector3d vector)
    {
        return new Vector3d(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3d add(Vector3d vector)
    {
        return new Vector3d(x + vector.x, y + vector.y, z + vector.z);
    }

    public String toString()
    {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof Vector3d)) return false;

        Vector3d vector = (Vector3d)object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    @Override
    public int hashCode()
    {
        return Double.hashCode(x) ^ Double.hashCode(y) << 2 ^ Double.hashCode(z) >> 2;
    }
}
