package helporme.armsforge.common.block.model.registry;

import helporme.armsforge.common.block.BlockMasterAnvil;
import helporme.armsforge.common.block.model.IModel;
import helporme.armsforge.common.block.model.ModelSuite;

import java.util.HashSet;
import java.util.Set;

public class ModelRegistry
{
    private static HashSet<ModelSuite> modelSuites = new HashSet<ModelSuite>();

    public static void createDefaultModelSuites()
    {
        addModelSuiteFrom(new BlockMasterAnvil());
    }

    public static void addModelSuiteFrom(IModel model)
    {
        addModelSuite(model.getModelSuite());
    }

    public static void addModelSuite(ModelSuite modelSuite)
    {
        modelSuites.add(modelSuite);
    }

    public static Iterable<ModelSuite> getAllModelSuites()
    {
        return (Iterable<ModelSuite>)modelSuites.clone();
    }
}
