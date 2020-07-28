package helporme.armsforge.common.blocks.base;

import helporme.armsforge.api.block.IMasterAnvil;
import net.minecraft.block.material.Material;

public abstract class MasterAnvilBase extends BlockModelBase implements IMasterAnvil
{
    public MasterAnvilBase(String name)
    {
        super(Material.anvil, name);
        setStepSound(soundTypeAnvil);
    }
}
