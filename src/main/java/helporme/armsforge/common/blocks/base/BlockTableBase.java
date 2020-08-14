package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityTableRenderer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTable;
import helporme.armsforge.forge.wrapper.utils.InventoryHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//TODO: Refactoring (Remove tries)

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

        ItemStack equippedItem = player.getCurrentEquippedItem();
        if (equippedItem != null && canPutItemOnTable(equippedItem, table))
        {
            putItemOnTable(equippedItem, table);
            if (equippedItem.stackSize == 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                player.inventory.markDirty();
            }
            return true;
        }

        if (canPutItemsFromTableInPlayer(table, player))
        {
            putItemsFromTableInPlayer(table, player);
            return true;
        }
        return false;
    }

    protected boolean canPutItemOnTable(ItemStack itemStack, TileEntityTable table)
    {
        int itemSlot = table.getItemSlot();
        return table.hasSpaceForItemInSlot(itemStack, itemSlot) && table.isItemValidForSlot(itemSlot, itemStack);
    }

    protected void putItemOnTable(ItemStack itemStack, TileEntityTable table)
    {
        int itemSlot = table.getItemSlot();

        if (table.isEmptyInSlot(itemSlot))
        {
            table.setInventorySlotContents(itemSlot, itemStack.splitStack(1));
        }
        else
        {
            table.fillSlotWithItem(itemSlot, itemStack);
        }
    }

    protected boolean canPutItemsFromTableInPlayer(TileEntityTable table, EntityPlayer player)
    {
        if (InventoryHelper.hasItems(table))
        {
            ItemStack firstItem = InventoryHelper.getFirstItem(table);
            return InventoryHelper.hasSpaceForItem(player.inventory, 27, firstItem);
        }
        return false;
    }

    protected void putItemsFromTableInPlayer(TileEntityTable table, EntityPlayer player)
    {
        player.inventory.addItemStackToInventory(InventoryHelper.popFirstItem(table));
        player.inventory.markDirty();
    }
}
