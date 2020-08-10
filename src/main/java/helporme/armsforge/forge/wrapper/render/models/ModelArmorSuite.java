package helporme.armsforge.forge.wrapper.render.models;

import helporme.armsforge.common.core.Version;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

public class ModelArmorSuite
{
    public IModelCustom head;
    public IModelCustom body;
    public IModelCustom rightArm;
    public IModelCustom leftArm;
    public IModelCustom rightLeg;
    public IModelCustom leftLeg;
    public IModelCustom rightBoot;
    public IModelCustom leftBoot;

    public ModelArmorSuite(String name)
    {
        ResourceLocation modelLocation = new ResourceLocation(Version.modid + ":" + name);
    }

    public ModelArmorSuite(IModelCustom head, IModelCustom body, IModelCustom rightArm, IModelCustom leftArm,
                           IModelCustom rightLeg, IModelCustom leftLeg, IModelCustom rightBoot, IModelCustom leftBoot)
    {
        this.head = head;
        this.body = body;
        this.rightArm = rightArm;
        this.leftArm = leftArm;
        this.rightLeg = rightLeg;
        this.leftLeg = leftLeg;
        this.rightBoot = rightBoot;
        this.leftBoot = leftBoot;
    }
}
