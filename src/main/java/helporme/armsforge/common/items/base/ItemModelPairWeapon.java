package helporme.armsforge.common.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.api.items.IPairWeaponMain;
import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.client.render.items.ItemPairWeaponRenderer;
import helporme.armsforge.forge.wrapper.render.models.IItemModelContainer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemModelPairWeapon extends ItemModelWeapon implements IItemModelContainer, IPairWeaponMain
{
    public ItemModelPairWeapon(String name, ToolMaterial material, String textureAtlas)
    {
        super(name, material, textureAtlas);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer(ModelInfo modelInfo)
    {
        return new ItemPairWeaponRenderer(modelInfo);
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
    public ItemStack getSubWeapon(EntityPlayer player)
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
