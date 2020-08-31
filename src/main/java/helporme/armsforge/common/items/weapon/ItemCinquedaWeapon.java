package helporme.armsforge.common.items.weapon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.client.render.items.ItemPairWeaponRenderer;
import helporme.armsforge.common.items.base.ItemModelPairWeapon;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraftforge.client.IItemRenderer;

public class ItemCinquedaWeapon extends ItemModelPairWeapon
{
    public ItemCinquedaWeapon()
    {
        super("Cinqueda", ToolMaterial.IRON, "SwordsAtlas");
    }
}
