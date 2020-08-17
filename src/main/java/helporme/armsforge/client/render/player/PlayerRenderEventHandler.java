package helporme.armsforge.client.render.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import helporme.armsforge.api.items.IPairWeaponMain;
import helporme.armsforge.api.items.ITwoHandedWeapon;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class PlayerRenderEventHandler
{
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Specials.Pre event)
    {
        ItemStack equippedItemStack = event.entityPlayer.inventory.getCurrentItem();
        if (equippedItemStack != null)
        {
            Item equippedItem = equippedItemStack.getItem();
            swapModelRenderersByItem(event.renderer, equippedItem);
            handlePairWeapon(event, equippedItem);
        }
    }

    public void swapModelRenderersByItem(RenderPlayer renderer, Item item)
    {
        ModelBiped[] models = new ModelBiped[] { renderer.modelBipedMain, renderer.modelArmorChestplate, renderer.modelArmor };
        for (ModelBiped modelBiped : models)
        {
            if (item instanceof ITwoHandedWeapon)
            {
                if (!(modelBiped.bipedRightArm instanceof RightHandSwapModel))
                {
                    modelBiped.bipedRightArm = new RightHandSwapModel(modelBiped);
                }
                if (!(modelBiped.bipedLeftArm instanceof LeftHandSwapModel))
                {
                    modelBiped.bipedLeftArm = new LeftHandSwapModel(modelBiped);
                }
                RightHandSwapModel rightHand = (RightHandSwapModel)modelBiped.bipedRightArm;
                rightHand.useSwappedRenderer = true;
                LeftHandSwapModel leftHand = (LeftHandSwapModel)modelBiped.bipedLeftArm;
                leftHand.useSwappedRenderer = true;
            }
        }
    }

    public void handlePairWeapon(RenderPlayerEvent.Specials.Pre event, Item item)
    {
        if (item instanceof IPairWeaponMain)
        {
            IPairWeaponMain pairWeaponMain = (IPairWeaponMain)item;
            if (pairWeaponMain.hasSubItem(event.entityPlayer))
            {
                ItemStack subWeapon = pairWeaponMain.getSubWeapon(event.entityPlayer);
                AbstractClientPlayer abstractClient = (AbstractClientPlayer)event.entityPlayer;
                renderItemInLeftHand(abstractClient, event.renderer.modelBipedMain, subWeapon);
                setBipedHeldLeftItemState(event.renderer, 1);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Post event)
    {
        ItemStack equippedItemStack = event.entityPlayer.inventory.getCurrentItem();
        if (equippedItemStack != null)
        {
            Item equippedItem = equippedItemStack.getItem();
            if (!(equippedItem instanceof IPairWeaponMain))
            {
                setBipedHeldLeftItemState(event.renderer, 0);
            }
            if (!(equippedItem instanceof ITwoHandedWeapon) && isEnabledSwapperRenderersExists(event.renderer))
            {
                turnOffSwappedRenderers(event.renderer);
            }
        }
        else
        {
            setBipedHeldLeftItemState(event.renderer, 0);
            if (isEnabledSwapperRenderersExists(event.renderer))
            {
                turnOffSwappedRenderers(event.renderer);
            }
        }
    }

    public void setBipedHeldLeftItemState(RenderPlayer renderPlayer, int state)
    {
        renderPlayer.modelArmorChestplate.heldItemLeft = state;
        renderPlayer.modelBipedMain.heldItemLeft = state;
        renderPlayer.modelArmor.heldItemLeft = state;
    }

    public boolean isEnabledSwapperRenderersExists(RenderPlayer renderer)
    {
        for (ModelRenderer modelRenderer : getModelRenderersFrom(renderer.modelBipedMain))
        {
            if (modelRenderer instanceof ModelRendererSwapWrapper)
            {
                ModelRendererSwapWrapper swappedModel = ((ModelRendererSwapWrapper)modelRenderer);
                if (swappedModel.useSwappedRenderer)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void turnOffSwappedRenderers(RenderPlayer renderer)
    {
        ModelBiped[] models = new ModelBiped[] { renderer.modelBipedMain, renderer.modelArmorChestplate, renderer.modelArmor };
        for (ModelBiped modelBiped : models)
        {
            for (ModelRenderer modelRenderer : getModelRenderersFrom(modelBiped))
            {
                if (modelRenderer instanceof ModelRendererSwapWrapper)
                {
                    ModelRendererSwapWrapper swappedModel = ((ModelRendererSwapWrapper)modelRenderer);
                    swappedModel.useSwappedRenderer = false;
                }
            }
        }
    }

    public ModelRenderer[] getModelRenderersFrom(ModelBiped modelBiped)
    {
        return new ModelRenderer[] {
            /*modelBiped.bipedBody, modelBiped.bipedHead,*/
                modelBiped.bipedRightArm, modelBiped.bipedLeftArm,
            /*modelBiped.bipedLeftLeg, modelBiped.bipedRightLeg*/ };
    }

    public void renderItemInLeftHand(AbstractClientPlayer player, ModelBiped modelBiped, ItemStack itemStack)
    {
        GL11.glPushMatrix();
        modelBiped.bipedLeftArm.postRender(0.0625F);
        GL11.glTranslatef(0.0625F, 0.4375F, 0.0625F);

        if (player.fishEntity != null)
        {
            itemStack = new ItemStack(Items.stick);
        }

        EnumAction enumaction = null;

        if (player.getItemInUseCount() > 0)
        {
            enumaction = itemStack.getItemUseAction();
        }

        net.minecraftforge.client.IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemStack, IItemRenderer.ItemRenderType.EQUIPPED);
        boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemStack, IItemRenderer.ItemRendererHelper.BLOCK_3D));

        float f2;
        float f4;

        if (is3D || itemStack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemStack.getItem()).getRenderType()))
        {
            f2 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            f2 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-f2, -f2, f2);
        }
        else if (itemStack.getItem() == Items.bow)
        {
            f2 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(f2, -f2, f2);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (itemStack.getItem().isFull3D())
        {
            f2 = 0.625F;

            if (itemStack.getItem().shouldRotateAroundWhenRendering())
            {
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if (player.getItemInUseCount() > 0 && enumaction == EnumAction.block)
            {
                GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
            }

            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(f2, -f2, f2);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            f2 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(f2, f2, f2);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
        }


        float f3;
        int k;
        float f12;

        if (itemStack.getItem().requiresMultipleRenderPasses())
        {
            for (k = 0; k < itemStack.getItem().getRenderPasses(itemStack.getItemDamage()); ++k)
            {
                int i = itemStack.getItem().getColorFromItemStack(itemStack, k);
                f12 = (float)(i >> 16 & 255) / 255.0F;
                f3 = (float)(i >> 8 & 255) / 255.0F;
                f4 = (float)(i & 255) / 255.0F;
                GL11.glColor4f(f12, f3, f4, 1.0F);
                RenderManager.instance.itemRenderer.renderItem(player, itemStack, k);
            }
        }
        else
        {
            k = itemStack.getItem().getColorFromItemStack(itemStack, 0);
            float f11 = (float)(k >> 16 & 255) / 255.0F;
            f12 = (float)(k >> 8 & 255) / 255.0F;
            f3 = (float)(k & 255) / 255.0F;
            GL11.glColor4f(f11, f12, f3, 1.0F);
            RenderManager.instance.itemRenderer.renderItem(player, itemStack, 0);
        }
        GL11.glPopMatrix();
    }
}
