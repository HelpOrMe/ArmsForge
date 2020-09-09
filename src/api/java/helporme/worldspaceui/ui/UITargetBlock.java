package helporme.worldspaceui.ui;

import helporme.worldspaceui.commands.ICommandSupported;
import helporme.worldspaceui.types.Vector3i;
import io.netty.buffer.ByteBuf;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class UITargetBlock extends UITarget implements ICommandSupported
{
    public Vector3i position;
    public int dimension;

    public UITargetBlock(Vector3i position, int dimension)
    {
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        position = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        dimension = buf.readInt();
    }

    @Override
    public void copyValues(UITarget target)
    {
        UITargetBlock uiTargetBlock = (UITargetBlock)target;
        position = uiTargetBlock.position;
        dimension = uiTargetBlock.dimension;
    }

    @Override
    public void initFromCommand(ICommandSender sender, String arg)
    {
        World world = sender.getEntityWorld();
        if (world != null)
        {
            EntityPlayer player = world.getPlayerEntityByName(sender.getCommandSenderName());
            if (player != null)
            {
                Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
                MovingObjectPosition hit = world.rayTraceBlocks(playerPos, player.getLookVec());
                if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    position = new Vector3i(hit.blockX, hit.blockY, hit.blockZ);
                    dimension = world.provider.dimensionId;
                }
            }
        }
    }

    @Override
    public void initFromCommand(String... args)
    {

    }
}
