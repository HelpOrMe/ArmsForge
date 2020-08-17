package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemHalberdWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemHalberdWeapon()
    {
        super("Halberd", ToolMaterial.IRON, "PolesAtlas");
    }
}
