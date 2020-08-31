package helporme.armsforge.client.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import helporme.armsforge.client.render.player.PlayerRenderEventHandler;
import helporme.armsforge.client.registry.ItemRendererRegistry;
import helporme.armsforge.client.registry.TileRendererRegistry;
import helporme.armsforge.common.core.proxy.CommonProxy;
import helporme.armsforge.common.registry.BlockModelsRegistry;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        BlockModelsRegistry.createBlockModelSuites();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        TileRendererRegistry.registerFromBlocks();
        ItemRendererRegistry.registerFromBlocks();
        ItemRendererRegistry.registerFromItems();

        MinecraftForge.EVENT_BUS.register(new PlayerRenderEventHandler());
    }
}
