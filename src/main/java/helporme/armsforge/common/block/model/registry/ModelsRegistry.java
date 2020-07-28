package helporme.armsforge.common.block.model.registry;

import helporme.armsforge.common.block.model.IModelContainer;
import helporme.armsforge.common.block.model.ModelSuite;
import helporme.armsforge.common.block.registry.BlocksRegistry;
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
