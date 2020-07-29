package helporme.armsforge.common.core.tab;

import helporme.armsforge.common.registry.BlocksRegistry;
import helporme.armsforge.common.core.Version;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ArmsForgeTab extends CreativeTabs
{
    public ArmsForgeTab()
    {
        super(Version.modid);
    }

    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(BlocksRegistry.getBlockByName("MasterAnvil"));
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return Version.name;
    }
}
