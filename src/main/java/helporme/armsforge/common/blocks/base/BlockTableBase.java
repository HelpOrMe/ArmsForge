package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.blocks.IMasterAnvil;
import helporme.armsforge.client.render.tiles.base.TileEntityTableRendererBase;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.tiles.base.TileEntityTableBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockTableBase extends BlockModelBase implements IMasterAnvil
{
    public BlockTableBase(Material material, String name)
    {
        super(material, name);
        setStepSound(soundTypeAnvil);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityTableRendererBase(modelInfo);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        //TODO: IActionAccessProvider
        TileEntityTableBase tile = (TileEntityTableBase)world.getTileEntity(x, y, z);
        return tryPutItemFromTableAt(player, tile) || tryPutItemOnTableFrom(player, tile);
    }

    protected boolean tryPutItemFromTableAt(EntityPlayer player, TileEntityTableBase tile)
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

    protected boolean tryPutItemOnTableFrom(EntityPlayer player, TileEntityTableBase tile)
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
