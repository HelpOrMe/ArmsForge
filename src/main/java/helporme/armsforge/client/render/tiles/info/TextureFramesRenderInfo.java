package helporme.armsforge.client.render.tiles.info;

public class TextureFramesRenderInfo
{
    public int curFrame = 0;
    public int frameSign = 1;
    public long nextTick = 0;

    public int getMaxFrame()
    {
        return 3;
    }
}
