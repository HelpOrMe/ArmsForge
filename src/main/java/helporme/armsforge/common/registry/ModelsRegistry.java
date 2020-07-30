package helporme.armsforge.common.registry;

import helporme.armsforge.common.blocks.models.IModelContainer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.blocks.models.ModelSuite;
import net.minecraft.block.Block;

import java.util.HashSet;

public final class ModelsRegistry
{
    private static HashSet<ModelSuite> modelSuites = new HashSet<ModelSuite>();

    public static void createModelSuitesFromBlocks()
    {
        for (Block block : BlocksRegistry.getAllBlocks())
        {
            if (block instanceof IModelContainer)
            {
                addModelSuite(getModelSuiteFrom((IModelContainer)block));
            }
        }
    }

    public static ModelSuite getModelSuiteFrom(IModelContainer model)
    {
        ModelInfo modelInfo = model.getModelInfo();
        return new ModelSuite(
                model.getBlock(), model.getTileClass(), model.getTileRenderer(modelInfo),
                model.getItemRenderer(modelInfo), modelInfo);
    }

    public static void addModelSuite(ModelSuite modelSuite)
    {
        modelSuites.add(modelSuite);
    }

    public static Iterable<ModelSuite> getAllModelSuites()
    {
        return new HashSet<ModelSuite>(modelSuites);
    }
}
