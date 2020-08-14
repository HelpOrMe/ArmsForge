package helporme.armsforge.common.items.base;

import helporme.armsforge.api.items.IPairWeaponMain;
import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.forge.wrapper.render.models.IItemModelContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemModelPairWeapon extends ItemModelWeapon implements IItemModelContainer, IPairWeaponMain
{
    public ItemModelPairWeapon(String name, ToolMaterial material, String textureAtlas)
    {
        super(name, material, textureAtlas);
    }

    @Override
    public boolean hasSubItem(EntityPlayer player)
    {
        if (player.getCurrentEquippedItem().getItem().equals(this))
        {
            int leftSlot = player.inventory.currentItem - 1;
            if (isHotbarSlotValid(leftSlot))
            {
                ItemStack itemStack = player.inventory.getStackInSlot(leftSlot);
                return itemStack != null && itemStack.getItem() instanceof IPairWeaponSub;
            }
        }
        return false;
    }

    @Override
    public ItemStack getSubItem(EntityPlayer player)
    {
        if (player.getCurrentEquippedItem().getItem().equals(this))
        {
            int leftSlot = player.inventory.currentItem - 1;
            if (isHotbarSlotValid(leftSlot))
            {
                return player.inventory.getStackInSlot(leftSlot);
            }
        }
        throw new IllegalArgumentException("Player has not sub weapon for " + this);
    }

    protected boolean isHotbarSlotValid(int slot)
    {
        return slot >= 0 && slot < 9;
    }
}
