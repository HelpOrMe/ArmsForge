package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemWarHammerWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemWarHammerWeapon()
    {
        super("WarHammer", ToolMaterial.IRON, "TwohandedAtlas");
    }
}
