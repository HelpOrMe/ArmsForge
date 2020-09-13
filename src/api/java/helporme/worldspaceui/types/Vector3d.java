package helporme.worldspaceui.types;

public class Vector3d
{
    public double x;
    public double y;
    public double z;

    public Vector3d() { }

    public Vector3d(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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
        return "(" +
                Math.round(x * 100d) / 100d + ", " +
                Math.round(y * 100d) / 100d + ", " +
                Math.round(z * 100d) / 100d + ")";
    }

    public void copyValues(Vector3d vector)
    {
        x = vector.x;
        y = vector.y;
        z = vector.z;
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
