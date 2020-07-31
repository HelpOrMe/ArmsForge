package helporme.armsforge.client.render.info;

import java.util.Random;

public class ThaumAnvilRenderInfo
{
    public int currentTextureFrame = 0;
    public int textureFramesCount = 3;
    public double timeBetweenFrames = 0;
    public double cooldownBetweenFrames = 8;

    public Random random = new Random(0);
}
