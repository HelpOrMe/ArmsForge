package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.armor.SandArmor;
import helporme.armsforge.common.items.armor.UnderArmor;

public class ArmorList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new UnderArmor(4, 0), new UnderArmor(4, 1),
                new UnderArmor(3, 2), new UnderArmor(4, 3),
                new SandArmor(4, 0), new SandArmor(4, 1),
                new SandArmor(3, 2), new SandArmor(4, 3));
    }
}
