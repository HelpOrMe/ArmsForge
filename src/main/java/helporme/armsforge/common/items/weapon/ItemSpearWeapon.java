package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemSpearWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemSpearWeapon()
    {
        super("Spear", ToolMaterial.IRON, "PolesAtlas");
    }
}
