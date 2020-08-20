package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemZweihanderWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemZweihanderWeapon()
    {
        super("Zweihander", ToolMaterial.IRON, "TwohandedAtlas");
    }
}
