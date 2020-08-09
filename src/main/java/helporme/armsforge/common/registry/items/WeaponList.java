package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.weapon.ItemKarolingSword;

public class WeaponList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new ItemKarolingSword()
        );
    }
}
