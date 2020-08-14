package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemMaceWeapon extends ItemModelWeapon implements IPairWeaponSub
{
    public ItemMaceWeapon()
    {
        super("Mace", ToolMaterial.IRON, "MacesAtlas");
    }
}
