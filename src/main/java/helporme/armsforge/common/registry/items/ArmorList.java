package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.armor.TestArmor;

public class ArmorList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new TestArmor(4, 0)
        );
    }
}
