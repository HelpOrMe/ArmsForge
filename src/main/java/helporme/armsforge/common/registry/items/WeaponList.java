package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.weapon.*;

public class WeaponList extends ItemList
{
    @Override
    public void addDefault()
    {
        addItems(
                new ItemCinquedaSword(),
                new ItemCommonSword(),
                new ItemErzacSword(),
                new ItemGladiusSword(),
                new ItemKarolingSword(),
                new ItemKatanaSword(),
                new ItemKopisSword(),
                new ItemNinzatoSword(),
                new ItemSabreSword(),
                new ItemScimitarSword()
        );
    }
}
