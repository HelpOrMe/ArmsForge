package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.blocks.tiles.ISupportTable;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.utils.Vector3;
import helporme.armsforge.api.items.IDebugTool;
import helporme.armsforge.client.render.items.ItemCraftingTableRenderer;
import helporme.armsforge.client.render.tiles.base.TileEntityCraftingTableRenderer;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.tiles.base.TileEntityCraftingTable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockCraftingTable extends BlockTableBase
{
    public BlockCraftingTable(Material material, String name)
    {
        super(material, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityCraftingTableRenderer(modelInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer(ModelInfo modelInfo)
    {
        return new ItemCraftingTableRenderer(modelInfo);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntityCraftingTable craftingTable = (TileEntityCraftingTable)world.getTileEntity(x, y, z);
        ItemStack equippedStack = player.getCurrentEquippedItem();

        if (equippedStack != null)
        {
            Item equippedItem = equippedStack.getItem();

            if (equippedItem instanceof IDebugTool)
            {
                if (world.isRemote)
                {
                    List<Vector3> targets = getParticlePositionsFrom(craftingTable.getSupportTablesNear());
                    ArmsForge.fxEngine.spawnDebugParticleVertLines(world, targets);
                }
                return true;
            }

            if (equippedItem instanceof IHammer)
            {
                if (!world.isRemote)
                {
                    craftingTable.onHammerBlow(player.inventory.getCurrentItem(), player);
                }
                return true;
            }

            if (canPutRecipeOnTable(equippedStack, craftingTable))
            {
                if (!world.isRemote)
                {
                    putRecipeOnTable(equippedStack, craftingTable);
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    player.inventory.markDirty();
                }
                return true;
            }
        }
        return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
    }

    protected boolean canPutRecipeOnTable(ItemStack recipeStack, TileEntityCraftingTable table)
    {
        return table.isEmptyInSlot(table.getRecipeSlot()) && table.isRecipeValid(recipeStack);
    }

    protected void putRecipeOnTable(ItemStack recipeStack, TileEntityCraftingTable table)
    {
        table.setInventorySlotContents(table.getRecipeSlot(), recipeStack);
    }

    protected List<Vector3> getParticlePositionsFrom(ISupportTable[] supportTables)
    {
        List<Vector3> positions = new ArrayList<>();
        Vector3 offset = new Vector3(0.5f, 1f, 0.5f);
        for (ISupportTable supportTable : supportTables)
        {
            positions.add(new Vector3(supportTable.getPosition()).add(offset));
        }
        return positions;
    }
}
