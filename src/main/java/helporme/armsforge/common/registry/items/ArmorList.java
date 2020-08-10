package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.armor.TestArmor;

public class ArmorList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new TestArmor(4, 0),
                new TestArmor(4, 1),
                new TestArmor(3, 2),
                new TestArmor(4, 3)
        );
    }
}
