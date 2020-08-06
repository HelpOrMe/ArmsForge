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
                if (table.isEmptyAt(slot) && table.isItemValidForSlot(slot, itemStackAtHand))
                {
                    table.setInventorySlotContents(slot, popItemFromHand(player, table.getInventoryStackLimit()));
                    player.inventory.markDirty();
                    return true;
                }
            }
        }
        return false;
    }

    protected ItemStack popItemFromHand(EntityPlayer player, int count)
    {
        return player.inventory.decrStackSize(player.inventory.currentItem, count);
    }

    protected boolean tryAddItemToPlayerFromTable(EntityPlayer player, TileEntityTable table)
    {
        for (int slot = 0; slot < table.getSizeInventory(); slot++)
        {
            ItemStack itemStackOnTable = table.getStackInSlot(slot);
            if (itemStackOnTable != null)
            {
                boolean playerHasSpaceForItem = InventoryHelper.hasSpaceFor(itemStackOnTable, player.inventory, 27);
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
