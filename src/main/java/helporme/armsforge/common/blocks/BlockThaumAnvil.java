package helporme.armsforge.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.tiles.TileEntityThaumAnvilRenderer;
import helporme.armsforge.common.blocks.base.BlockCraftingTable;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.tiles.TileEntityThaumAnvil;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockThaumAnvil extends BlockCraftingTable
{
    public BlockThaumAnvil()
    {
        super(Material.anvil,"ThaumAnvil");
        setHardness(12.5f);
        setResistance(12.5f);
        setHarvestLevel("pickaxe", 2);
        setBlockBounds(0.11f, 0f, 0.11f, 0.89f, 1f, 0.89f);
     }

    @Override
    public Class<? extends TileEntity> getTileClass()
    {
        return TileEntityThaumAnvil.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityThaumAnvil();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TileEntitySpecialRenderer getTileRenderer(ModelInfo modelInfo)
    {
        return new TileEntityThaumAnvilRenderer(modelInfo);
    }
}
