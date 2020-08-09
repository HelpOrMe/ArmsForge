package helporme.armsforge.api.utils;

public class Vector3Int
{
    public static final Vector3Int max = new Vector3Int(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    public static final Vector3Int min = new Vector3Int(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    public int x;
    public int y;
    public int z;

    public Vector3Int(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3Int divide(int value)
    {
        return new Vector3Int(x / value, y / value, z / value);
    }

    public Vector3Int multiply(int value)
    {
        return new Vector3Int(x * value, y * value, z * value);
    }

    public Vector3Int minus(Vector3Int vector)
    {
        return new Vector3Int(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3Int add(Vector3Int vector)
    {
        return new Vector3Int(x + vector.x, y + vector.y, z + vector.z);
    }

    public String toString()
    {
        return "Vector3Int(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this) return true;
        if (!(object instanceof Vector3Int)) return false;

        Vector3Int vector = (Vector3Int)object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    @Override
    public int hashCode()
    {
        return x ^ y << 2 ^ z >> 2;
    }
}
