package helporme.armsforge.common.block;

import helporme.armsforge.api.block.IMasterAnvil;
import net.minecraft.block.material.Material;

public abstract class MasterAnvilBase extends BlockContainerBase implements IMasterAnvil
{
    public MasterAnvilBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeAnvil);
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
