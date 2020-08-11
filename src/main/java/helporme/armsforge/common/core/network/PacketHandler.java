package helporme.armsforge.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.core.network.fx.PacketCanceledFX;
import helporme.armsforge.common.core.network.fx.PacketItemFX;
import helporme.armsforge.common.core.network.fx.PacketSuccessfulFX;

public class PacketHandler extends SimpleNetworkWrapper
{
    protected int lastId = 1;

    public PacketHandler()
    {
        super(Version.modid);
    }

    public void init()
    {
        registerMessage(PacketCanceledFX.class, PacketCanceledFX.class, lastId++, Side.CLIENT);
        registerMessage(PacketItemFX.class, PacketItemFX.class, lastId++, Side.CLIENT);
        registerMessage(PacketSuccessfulFX.class, PacketSuccessfulFX.class, lastId++, Side.CLIENT);
    }
}
