package helporme.armsforge.common.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.base.TileEntityCraftingTableHighRenderer;
import helporme.armsforge.forge.wrapper.models.ModelInfo;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class BlockCraftingTableHigh extends BlockCraftingTable
{
    public BlockCraftingTableHigh(Material material, String name)
    {
        super(material, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityCraftingTableHighRenderer(modelInfo);
    }
}
