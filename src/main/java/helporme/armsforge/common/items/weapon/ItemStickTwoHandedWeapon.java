package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemStickTwoHandedWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemStickTwoHandedWeapon()
    {
        super("StickTwoHanded", ToolMaterial.IRON, "StickTwoHanded");
    }
}
