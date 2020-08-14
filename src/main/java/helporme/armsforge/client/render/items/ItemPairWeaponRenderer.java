package helporme.armsforge.client.render.items;

import helporme.armsforge.api.items.IPairWeaponMain;
import helporme.armsforge.api.items.IPairWeaponSub;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPairWeaponRenderer extends ItemWeaponRenderer
{
    public ItemPairWeaponRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
    }

    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data)
    {
        super.renderItem(type, itemStack, data);

        boolean isCallFromMainPair = data.length > 0 && data[data.length - 1] == "MainPairCall";
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON && !isCallFromMainPair)
        {
            Item equippedItem = itemStack.getItem();
            if (itemStack.getItem() instanceof IPairWeaponMain)
            {
                IPairWeaponMain weaponMainPair = (IPairWeaponMain)equippedItem;
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if (player.getCurrentEquippedItem() != null && weaponMainPair.hasSubItem(player))
                {
                    ItemStack pairWeaponSubStack = weaponMainPair.getSubItem(player);
                    IPairWeaponSub pairWeaponSub = (IPairWeaponSub)pairWeaponSubStack.getItem();
                    IItemRenderer weaponSubRenderer = pairWeaponSub.getItemRenderer(pairWeaponSub.getModelInfo());
                    GL11.glPushMatrix();
                    GL11.glTranslatef(-2.1f, 0, -2.1f);
                    weaponSubRenderer.renderItem(type, pairWeaponSubStack, data, "MainPairCall");
                    GL11.glPopMatrix();
                }
            }
        }
    }
}
