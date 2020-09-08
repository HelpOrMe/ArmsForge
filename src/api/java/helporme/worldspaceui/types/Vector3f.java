package helporme.worldspaceui.types;

public class Vector3f
{
    public float x;
    public float y;
    public float z;

    public Vector3f() { }

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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
        return "Vector3f(" + x + ", " + y + ", " + z + ")";
    }

    public void copyValues(Vector3f vector)
    {
        x = vector.x;
        y = vector.y;
        z = vector.z;
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
