package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemTowerShieldWeapon extends ItemModelWeapon implements IPairWeaponSub
{
    public ItemTowerShieldWeapon()
    {
        super("TowerShield", ToolMaterial.IRON, "ShielsAtlas");
    }
}
