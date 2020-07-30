package helporme.armsforge.common.blocks.base;

import helporme.armsforge.api.blocks.IMasterAnvil;
import helporme.armsforge.common.tiles.base.TileEntityCraftingPlaceBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockCraftingPlaceBase extends BlockModelBase implements IMasterAnvil
{
    public BlockCraftingPlaceBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeAnvil);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote)
        {
            TileEntityCraftingPlaceBase tile = (TileEntityCraftingPlaceBase)world.getTileEntity(x, y, z);

            ItemStack itemStackFromTable = tile.getStackInSlot(0);
            if (itemStackFromTable != null && player.inventory.addItemStackToInventory(itemStackFromTable))
            {
                tile.decrStackSize(0, itemStackFromTable.stackSize);
                player.inventory.markDirty();
            }
            else
            {
                if (player.inventory.getCurrentItem() != null)
                {
                    tile.setInventorySlotContents(0, player.inventory.decrStackSize(player.inventory.currentItem, 1));
                    player.inventory.markDirty();
                }
            }
            return true;
        }
        return false;
    }
}
