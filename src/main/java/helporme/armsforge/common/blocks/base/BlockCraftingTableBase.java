package helporme.armsforge.common.blocks.base;

import helporme.armsforge.api.blocks.tables.ISupportTable;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.items.IRecipeItem;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.items.IDebugTool;
import helporme.armsforge.common.core.utils.DebugHelper;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BlockCraftingTableBase extends BlockTableBase
{
    //TODO: Config
    public final int tableFoundRadius = 9;

    public BlockCraftingTableBase(Material material, String name)
    {
        super(material, name);
    }

    @Override
    public ItemStack getItemFromTable(TileEntityTableBase table)
    {
        if (table.getStackInSlot(1) == null)
        {
            return table.popItem(1);
        }
        return super.getItemFromTable(table);
    }

    @Override
    public void addItemToTable(ItemStack itemStack, TileEntityTableBase table)
    {
        int slot = itemStack.getItem() instanceof IRecipeItem ? 1 : 0;
        table.setInventorySlotContents(slot, itemStack);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (playerHasDebugToolAtHands(player))
        {
            return processDebugToolLogic(world, x, y, z);
        }
        if (playerHasHammerAtHands(player))
        {
            return processHammerLogic(world, x, y, z, player);
        }
        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    protected boolean playerHasDebugToolAtHands(EntityPlayer player)
    {
        ItemStack itemStackInHand = player.inventory.getCurrentItem();
        return itemStackInHand != null && itemStackInHand.getItem() instanceof IDebugTool;
    }

    protected boolean processDebugToolLogic(World world, int x, int y, int z)
    {
        if (world.isRemote)
        {
            ICraftingTable craftingTable = (ICraftingTable)world.getTileEntity(x, y, z);
            List<Vector3> targets = getPositionsFrom(craftingTable.getSupportTablesNear(tableFoundRadius));
            DebugHelper.spawnDebugParticleVertLines(world, targets);
        }
        return true;
    }

    protected boolean playerHasHammerAtHands(EntityPlayer player)
    {
        ItemStack itemStackInHand = player.inventory.getCurrentItem();
        return itemStackInHand != null && itemStackInHand.getItem() instanceof IHammer;
    }

    protected boolean processHammerLogic(World world, int x, int y, int z, EntityPlayer player)
    {
        if (world.isRemote)
        {
            ICraftingTable craftingTable = (ICraftingTable)world.getTileEntity(x, y, z);
            craftingTable.onHammerBlow((IHammer)player.inventory.getCurrentItem().getItem());
        }
        return true;
    }

    protected List<Vector3> getPositionsFrom(Set<ISupportTable> supportTables)
    {
        List<Vector3> positions = new ArrayList<>();
        Vector3 offset = new Vector3(0.5f, 1f, 0.5f);
        for (ISupportTable supportTable : supportTables)
        {
            positions.add(supportTable.getPosition().add(offset));
        }
        return positions;
    }
}
