package helporme.worldspaceui.ui.target;

import helporme.worldspaceui.types.Vector3d;
import helporme.worldspaceui.ui.UITarget;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;

public class UITargetBlock extends UITarget
{
    public Block block;
    public Vector3d position;
    public int dimension;

    public UITargetBlock(Block block, Vector3d position, int dimension)
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
        position = new Vector3d(buf.readInt(), buf.readInt(), buf.readInt());
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
}
