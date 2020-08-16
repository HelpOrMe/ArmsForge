package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemDegenWeapon extends ItemModelWeapon implements IPairWeaponSub
{
    public ItemDegenWeapon()
    {
        super("Degen", ToolMaterial.IRON, "DaggersAtlas");
    }
}
