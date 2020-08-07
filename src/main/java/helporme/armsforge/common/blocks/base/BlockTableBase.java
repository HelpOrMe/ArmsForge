package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityTableRenderer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import helporme.armsforge.forge.wrapper.utils.InventoryHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockTableBase extends BlockModelBase
{
    public BlockTableBase(Material material, String name)
    {
        super(material, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityTableRenderer(modelInfo);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntityTable table = (TileEntityTable)world.getTileEntity(x, y, z);
        return tryAddItemToTableFromPlayer(table, player) || tryAddItemToPlayerFromTable(player, table);
    }

    protected boolean tryAddItemToTableFromPlayer(TileEntityTable table, EntityPlayer player)
    {
        ItemStack itemStackAtHand = player.inventory.getCurrentItem();
        if (itemStackAtHand != null)
        {
            for (int slot = 0; slot < table.getSizeInventory(); slot++)
            {
                if (table.isItemValidForSlot(slot, itemStackAtHand))
                {
                    return tryAddOneItemToTableFromPlayer(table, slot, player) ||
                            tryFillStackAtTableByPlayer(table, slot, player);
                }
            }
        }
        return false;
    }

    protected boolean tryAddOneItemToTableFromPlayer(TileEntityTable table, int slot, EntityPlayer player)
    {
        if (table.isEmptyAt(slot))
        {
            table.setInventorySlotContents(slot, popItemFromHand(player));
            player.inventory.markDirty();
            return true;
        }
        return false;
    }

    protected boolean tryFillStackAtTableByPlayer(TileEntityTable table, int slot, EntityPlayer player)
    {
        ItemStack itemStackAtHand = player.inventory.getCurrentItem();
        ItemStack itemStackOnTable = table.getStackInSlot(slot);

        if (itemStackOnTable.isItemEqual(itemStackAtHand))
        {
            InventoryHelper.fillSlotWithItem(table, slot, itemStackAtHand);
            if (itemStackAtHand.stackSize == 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
            }
            return true;
        }
        return false;
    }

    protected ItemStack popItemFromHand(EntityPlayer player)
    {
        return player.inventory.decrStackSize(player.inventory.currentItem, 1);
    }

    protected boolean tryAddItemToPlayerFromTable(EntityPlayer player, TileEntityTable table)
    {
        for (int slot = 0; slot < table.getSizeInventory(); slot++)
        {
            ItemStack itemStackOnTable = table.getStackInSlot(slot);
            if (itemStackOnTable != null)
            {
                boolean playerHasSpaceForItem = InventoryHelper.hasSpaceForItem(itemStackOnTable, player.inventory, 27);
                if (playerHasSpaceForItem)
                {
                    player.inventory.addItemStackToInventory(table.popItemAt(slot));
                    player.inventory.markDirty();
                    return true;
                }
            }
        }
        return false;
    }
}
