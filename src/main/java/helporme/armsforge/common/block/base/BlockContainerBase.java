package helporme.armsforge.common.block.base;

import helporme.armsforge.common.block.registry.INamedBlock;
import helporme.armsforge.common.core.ArmsForge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer implements INamedBlock
{
    protected String name;

    protected BlockContainerBase(Material material, String name)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setBlockName(name);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntity();
    }
}
