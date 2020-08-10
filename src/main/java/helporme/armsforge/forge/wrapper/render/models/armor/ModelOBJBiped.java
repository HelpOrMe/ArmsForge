package helporme.armsforge.forge.wrapper.render.models.armor;

import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ModelOBJBiped extends ModelBiped
{
    protected ModelInfo modelInfo;

    public ModelOBJBiped(ModelInfo modelInfo, float f, float pY, int width, int height)
    {
        this.modelInfo = modelInfo;
        textureWidth = width;
        textureHeight = height;

        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedHeadwear = new ModelRenderer(this, 32, 0);
        bipedEars = new ModelRenderer(this, 24, 0);

        bipedHead = new ModelOBJHelmet(this, modelInfo.model, 0, 0);
        bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        bipedHead.setRotationPoint(0.0f, pY, 0.0f);

        bipedBody = new ModelOBJChest(this, modelInfo.model, 16, 16);
        bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        bipedBody.setRotationPoint(0.0f, pY, 0.0f);

        bipedRightArm = new ModelOBJRightArm(this, modelInfo.model, 40, 16);
        bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        bipedRightArm.setRotationPoint(-5.0f, 2.0f + pY, 0.0f);

        bipedLeftArm = new ModelOBJLeftArm(this, modelInfo.model, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        bipedLeftArm.setRotationPoint(5.0f, 2.0f + pY, 0.0f);

        bipedRightLeg = new ModelOBJRightLeg(this, modelInfo.model, 0, 16);
        bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        bipedRightLeg.setRotationPoint(-1.9f, 12.0f + pY, 0.0f);

        bipedLeftLeg = new ModelOBJLeftLeg(this, modelInfo.model, 0, 16);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        bipedLeftLeg.setRotationPoint(1.9f, 12.0f + pY, 0.0f);
    }

    public void selectArmorType(int armorSlot)
    {
        bipedHead.showModel = armorSlot == 0;
        bipedHeadwear.showModel = armorSlot == 0;
        bipedBody.showModel = armorSlot == 1;
        bipedRightArm.showModel = armorSlot == 1;
        bipedLeftArm.showModel = armorSlot == 1;
        bipedRightLeg.showModel = armorSlot == 2;
        bipedLeftLeg.showModel = armorSlot == 2;

        ModelOBJRightLeg OBJRightLeg = (ModelOBJRightLeg)bipedRightLeg;
        OBJRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
        OBJRightLeg.showLeg = armorSlot == 2;
        OBJRightLeg.showBoot = armorSlot == 3;

        ModelOBJLeftLeg OBJLeftLeg = (ModelOBJLeftLeg)bipedLeftLeg;
        OBJLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
        OBJLeftLeg.showLeg = armorSlot == 2;
        OBJLeftLeg.showBoot = armorSlot == 3;
    }

    @Override
    public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        updateBaseState(entity);
        Minecraft.getMinecraft().renderEngine.bindTexture(modelInfo.texture);
        GL11.glPushMatrix();
        super.render(entity, f1, f2, f3, f4, f5, f6);
        GL11.glPopMatrix();
    }

    public void updateBaseState(Entity entity)
    {
        isRiding = entity.isRiding();
        onGround = entity.onGround ? -10000f : 0;
        isSneak = entity.isSneaking();

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            heldItemRight = player.getHeldItem() != null ? 1 : 0;

            ItemStack itemInUse = player.getItemInUse();
            if (itemInUse != null)
            {
                aimedBow = itemInUse.getItem() instanceof ItemBow;
            }
        }
    }

    @Override
    public void renderEars(float p_78110_1_) { }

    @Override
    public void renderCloak(float p_78111_1_) { }
}
