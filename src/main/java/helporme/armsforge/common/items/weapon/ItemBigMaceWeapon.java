package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemBigMaceWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemBigMaceWeapon()
    {
        super("BigMace", ToolMaterial.IRON, "TwohandedAtlas");
    }
}
