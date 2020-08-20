package helporme.armsforge.common.items.weapon;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import helporme.armsforge.common.items.base.ItemModelWeapon;

public class ItemPoleaxeWeapon extends ItemModelWeapon implements ITwoHandedWeapon
{
    public ItemPoleaxeWeapon()
    {
        super("Poleaxe", ToolMaterial.IRON, "TwohandedAtlas");
    }
}
