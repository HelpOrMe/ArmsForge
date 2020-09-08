package helporme.worldspaceui.types;

public class Vector3i
{
    public int x;
    public int y;
    public int z;

    public Vector3i() { }

    public Vector3i(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i divide(int value)
    {
        return new Vector3i(x / value, y / value, z / value);
    }

    public Vector3i multiply(int value)
    {
        return new Vector3i(x * value, y * value, z * value);
    }

    public Vector3i minus(Vector3i vector)
    {
        return new Vector3i(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3i add(Vector3i vector)
    {
        return new Vector3i(x + vector.x, y + vector.y, z + vector.z);
    }

    public String toString()
    {
        return "Vector3i(" + x + ", " + y + ", " + z + ")";
    }

    public Vector3i copy()
    {
        return new Vector3i(x, y, z);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof Vector3i)) return false;

        Vector3i vector = (Vector3i)object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    @Override
    public int hashCode()
    {
        return x ^ y << 2 ^ z >> 2;
    }
}
