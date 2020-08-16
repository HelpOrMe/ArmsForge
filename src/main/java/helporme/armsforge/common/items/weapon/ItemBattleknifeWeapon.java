package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemBattleknifeWeapon extends ItemModelWeapon implements IPairWeaponSub
{
    public ItemBattleknifeWeapon()
    {
        super("Battleknife", ToolMaterial.IRON, "DaggersAtlas");
    }
}
