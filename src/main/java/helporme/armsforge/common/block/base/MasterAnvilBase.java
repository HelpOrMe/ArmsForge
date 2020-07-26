package helporme.armsforge.common.block.base;

import helporme.armsforge.api.block.IMasterAnvil;
import helporme.armsforge.common.block.base.BlockContainerBase;
import net.minecraft.block.material.Material;

public abstract class MasterAnvilBase extends BlockContainerBase implements IMasterAnvil
{
    public MasterAnvilBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeMetal);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
