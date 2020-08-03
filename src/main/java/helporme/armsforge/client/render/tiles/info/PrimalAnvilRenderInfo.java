package helporme.armsforge.client.render.tiles.info;

public class PrimalAnvilRenderInfo extends TextureFramesRenderInfo
{
    public int currentTextureFrame = 0;
    public int textureFramesCount = 4;
    public int textureFrameSign = 1;
    public double cooldownBetweenFrames = 10;
    public double timeBetweenFrames = 0;

    public double anvilYOffsetSpeed = 0.001d;
    public double maxAnvilYOffset = 0.05d;
    public double anvilYOffset = 0;
    public int anvilYOffsetSign = 1;

    public double chainRotationSpeed = 2d;
    public double chainRotationAngle = 0;
}