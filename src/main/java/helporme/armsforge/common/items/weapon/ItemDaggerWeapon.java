package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemDaggerWeapon extends ItemModelWeapon implements IPairWeaponSub
{
    public ItemDaggerWeapon()
    {
        super("Dagger", ToolMaterial.IRON, "DaggersAtlas");
    }
}
