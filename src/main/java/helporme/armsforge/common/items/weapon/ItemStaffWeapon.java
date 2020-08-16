package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemStaffWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemStaffWeapon()
    {
        super("Staff", ToolMaterial.IRON, "PolesAtlas");
    }
}
