package helporme.worldspaceui.ui;

import helporme.worldspaceui.commands.ICommandSupported;
import helporme.worldspaceui.types.Vector3i;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class UITargetBlock extends UITarget implements ICommandSupported
{
    public Block block;
    public Vector3i position;
    public int dimension;

    public UITargetBlock(Block block, Vector3i position, int dimension)
    {
        this.block = block;
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(Block.getIdFromBlock(block));
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        block = Block.getBlockById(buf.readInt());
        position = new Vector3i(buf.readInt(), buf.readInt(), buf.readInt());
        dimension = buf.readInt();
    }

    @Override
    public void copyValues(UITarget target)
    {
        UITargetBlock uiTargetBlock = (UITargetBlock)target;
        block = uiTargetBlock.block;
        position = uiTargetBlock.position;
        dimension = uiTargetBlock.dimension;
    }

    @Override
    public void initFromCommand(ICommandSender sender, String arg)
    {
        World world = sender.getEntityWorld();
        EntityPlayer player = world.getPlayerEntityByName(sender.getCommandSenderName());

        if (player != null)
        {
            Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
            MovingObjectPosition hit = world.rayTraceBlocks(playerPos, player.getLookVec());
        }
    }
}
