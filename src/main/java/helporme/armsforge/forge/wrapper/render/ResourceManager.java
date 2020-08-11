package helporme.armsforge.forge.wrapper.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.core.Version;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public final class ResourceManager
{
    private static final Map<String, ResourceLocation> resources = new HashMap<>();

    public static ResourceLocation get(String path)
    {
        if (!resources.containsKey(path))
        {
            resources.put(path, new ResourceLocation(Version.modid, path));
        }
        return resources.get(path);
    }

    public static void reset()
    {
        resources.clear();;
    }
}
