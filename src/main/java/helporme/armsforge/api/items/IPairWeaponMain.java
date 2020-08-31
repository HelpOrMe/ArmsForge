package helporme.armsforge.api.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPairWeaponMain
{
    boolean hasSubItem(EntityPlayer player);

    ItemStack getSubWeapon(EntityPlayer player);
}
