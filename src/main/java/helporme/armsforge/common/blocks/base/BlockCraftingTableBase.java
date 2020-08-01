package helporme.armsforge.common.blocks.base;

import helporme.armsforge.api.Vector3;
import helporme.armsforge.api.blocks.ICraftingTable;
import helporme.armsforge.api.blocks.ISupportTable;
import helporme.armsforge.api.blocks.ITable;
import helporme.armsforge.api.items.misc.IDebugTool;
import helporme.armsforge.common.core.utils.DebugHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockCraftingTableBase extends BlockTableBase
{
    //TODO: Config
    public int tableFoundRadius = 9;

    public BlockCraftingTableBase(Material material, String name)
    {
        super(material, name);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (playerHasDebugToolAtHand(player))
        {
            ICraftingTable craftingTable = (ICraftingTable)world.getTileEntity(x, y, z);
            if (world.isRemote)
            {
                Vector3 start = craftingTable.getPosition();
                List<Vector3> targets = getPositionsFrom(craftingTable.getSupportTablesNear(tableFoundRadius));
                DebugHelper.spawnDebugParticleVertLines(world, targets);
            }
            return true;
        }
        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    protected boolean playerHasDebugToolAtHand(EntityPlayer player)
    {
        ItemStack itemStackInHand = player.inventory.getCurrentItem();
        return itemStackInHand != null && itemStackInHand.getItem() instanceof IDebugTool;
    }

    protected List<Vector3> getPositionsFrom(List<ISupportTable> tables)
    {
        List<Vector3> positions = new ArrayList<Vector3>();
        Vector3 offset = new Vector3(0.5f, 1f, 0.5f);
        for (ITable table : tables)
        {
            positions.add(table.getPosition().add(offset));
        }
        return positions;
    }
}
