package helporme.armsforge.common.blocks.base;

import helporme.armsforge.api.blocks.IMasterAnvil;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTableBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockCraftingTableBase extends BlockModelBase implements IMasterAnvil
{
    public BlockCraftingTableBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeAnvil);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntityCraftingTableBase tile = (TileEntityCraftingTableBase)world.getTileEntity(x, y, z);
        return tryPutItemFromTableAt(player, tile) || tryPutItemOnTableFrom(player, tile);
    }

    protected boolean tryPutItemFromTableAt(EntityPlayer player, TileEntityCraftingTableBase tile)
    {
        ItemStack itemStackFromTable = tile.getStackInSlot(0);
        if (itemStackFromTable != null && player.inventory.addItemStackToInventory(itemStackFromTable))
        {
            tile.setInventorySlotContents(0, null);
            player.inventory.markDirty();
            return true;
        }
        return false;
    }

    protected boolean tryPutItemOnTableFrom(EntityPlayer player, TileEntityCraftingTableBase tile)
    {
        ItemStack itemStackFromTable = tile.getStackInSlot(0);
        if (itemStackFromTable == null && player.inventory.getCurrentItem() != null)
        {
            tile.setInventorySlotContents(0, player.inventory.decrStackSize(player.inventory.currentItem, 1));
            player.inventory.markDirty();
            return true;
        }
        return false;
    }
}
