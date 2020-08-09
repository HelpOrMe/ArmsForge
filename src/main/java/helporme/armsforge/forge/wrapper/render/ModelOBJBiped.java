package helporme.armsforge.forge.wrapper.render;

import helporme.armsforge.forge.wrapper.models.ModelArmorSuite;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelOBJBiped extends ModelBiped
{
    public ModelRenderer bipedRightBoot;
    public ModelRenderer bipedLeftBoot;

    public ModelOBJBiped(ModelArmorSuite suite, float f, float pY, int width, int height)
    {
        textureWidth = width;
        textureHeight = height;

        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedCloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, f);

        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, f);

        bipedHead = new ModelOBJRenderer(suite.head, this, 0, 0);
        bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        bipedHead.setRotationPoint(0.0f, pY, 0.0f);

        bipedHeadwear = new ModelRenderer(this, 32, 0);
        bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        bipedHeadwear.setRotationPoint(0.0f, pY, 0.0f);

        bipedBody = new ModelOBJRenderer(suite.body, this, 16, 16);
        bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        bipedBody.setRotationPoint(0.0f, pY, 0.0f);

        bipedRightArm = new ModelOBJRenderer(suite.rightArm, this, 40, 16);
        bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        bipedRightArm.setRotationPoint(-5.0f, 2.0f + pY, 0.0f);

        bipedLeftArm = new ModelOBJRenderer(suite.leftArm, this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        bipedLeftArm.setRotationPoint(5.0f, 2.0f + pY, 0.0f);

        bipedRightLeg = new ModelOBJRenderer(suite.rightLeg, this, 0, 16);
        bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        bipedRightLeg.setRotationPoint(-1.9f, 12.0f + pY, 0.0f);

        bipedLeftLeg = new ModelOBJRenderer(suite.leftLeg, this, 0, 16);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        bipedLeftLeg.setRotationPoint(1.9f, 12.0f + pY, 0.0f);
    }

    public void selectArmorType(int armorSlot)
    {
        bipedHead.showModel = armorSlot == 0;
        bipedHeadwear.showModel = armorSlot == 0;
        bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
        bipedRightArm.showModel = armorSlot == 1;
        bipedLeftArm.showModel = armorSlot == 1;
        bipedRightLeg.showModel = armorSlot == 2;
        bipedLeftLeg.showModel = armorSlot == 2;
        // bipedRightBoot.showModel = armorSlot == 3;
        // bipedLeftBoot.showModel = armorSlot == 3;
    }
}
