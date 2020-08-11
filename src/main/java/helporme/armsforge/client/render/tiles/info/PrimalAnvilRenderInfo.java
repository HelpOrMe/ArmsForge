package helporme.armsforge.client.render.tiles.info;

public class PrimalAnvilRenderInfo extends TextureFramesRenderInfo
{
    public final float anvilTickDistance = 0.01f;
    public final float maxAnvilYTicks = 0.05f;
    public float anvilYTick = 0;
    public int anvilYSign = 1;

    public float chainRotationAngle = 0;

    @Override
    public int getMaxFrame()
    {
        return 4;
    }
}
