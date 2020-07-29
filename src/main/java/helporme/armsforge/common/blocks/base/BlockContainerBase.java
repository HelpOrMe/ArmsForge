package helporme.armsforge.common.blocks.base;

import helporme.armsforge.common.core.ArmsForge;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.registry.interfaces.INamed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class BlockContainerBase extends BlockContainer implements INamed
{
    protected String name;

    protected BlockContainerBase(Material material, String name)
    {
        super(material);
        this.name = name;
        setCreativeTab(ArmsForge.tab);
        setBlockName(name);
        setBlockTextureName(Version.modid + ":" + name);
    }

    public String getName()
    {
        return name;
    }

    public abstract Class<? extends TileEntity> getTileClass();
}
