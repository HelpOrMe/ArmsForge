package helporme.armsforge.forge.wrapper.models;

public class ModelArmorSuite
{
    public final ModelInfo head;
    public final ModelInfo body;
    public final ModelInfo rightArm;
    public final ModelInfo leftArm;
    public final ModelInfo rightLeg;
    public final ModelInfo leftLeg;
    public final ModelInfo rightBoot;
    public final ModelInfo leftBoot;

    public ModelArmorSuite(ModelInfo head, ModelInfo body, ModelInfo rightArm, ModelInfo leftArm,
                           ModelInfo rightLeg, ModelInfo leftLeg, ModelInfo rightBoot, ModelInfo leftBoot)
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
