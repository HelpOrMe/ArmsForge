package helporme.armsforge.common.blocks.models.registry;

import helporme.armsforge.common.blocks.models.IModelContainer;
import helporme.armsforge.common.blocks.models.ModelSuite;
import helporme.armsforge.common.blocks.registry.BlocksRegistry;
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
                addModelSuiteFrom((IModelContainer)block);
            }
        }
    }

    public static void addModelSuiteFrom(IModelContainer model)
    {
        addModelSuite(model.getModelSuite());
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
