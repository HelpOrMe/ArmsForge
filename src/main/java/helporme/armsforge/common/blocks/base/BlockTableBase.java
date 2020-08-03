package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityTableRenderer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
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
        if (ArmsForge.accessProvider.canInteractWith(player, world, x, y, z))
        {
            TileEntityTableBase table = (TileEntityTableBase)world.getTileEntity(x, y, z);
            return tryAddItemToTableFromPlayer(table, player) || tryAddItemToPlayerFromTable(player, table);
        }
        return false;
    }

    protected boolean tryAddItemToTableFromPlayer(TileEntityTableBase table, EntityPlayer player)
    {
        ItemStack itemStackAtHand = player.inventory.getCurrentItem();
        boolean playerHasItem = itemStackAtHand != null;
        if (playerHasItem)
        {
            boolean tableHasSpaceForItem = table.hasSpaceForItem(itemStackAtHand);
            if (tableHasSpaceForItem)
            {
                addItemToTable(popItemFromHand(player, table.getInventoryStackLimit()), table);
                player.inventory.markDirty();
                return true;
            }
        }
        return false;
    }

    protected ItemStack popItemFromHand(EntityPlayer player, int count)
    {
        return player.inventory.decrStackSize(player.inventory.currentItem, count);
    }

    public void addItemToTable(ItemStack itemStack, TileEntityTableBase table)
    {
        table.setInventorySlotContents(0, itemStack);
    }

    protected boolean tryAddItemToPlayerFromTable(EntityPlayer player, TileEntityTableBase table)
    {
        boolean tableHasItem = !table.isEmptyAt(0);
        if (tableHasItem)
        {
            ItemStack itemStackOnTable = table.getStackInSlot(0);
            boolean playerHasSpaceForItem = InventoryHelper.hasSpaceForItem(itemStackOnTable, player.inventory, 27);
            if (playerHasSpaceForItem)
            {
                player.inventory.addItemStackToInventory(getItemFromTable(table));
                player.inventory.markDirty();
                return true;
            }
        }
        return false;
    }

    public ItemStack getItemFromTable(TileEntityTableBase table)
    {
        return table.popItem(0);
    }
}
