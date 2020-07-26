package helporme.armsforge.common.block.model.registry;

import helporme.armsforge.common.block.BlockMasterAnvil;
import helporme.armsforge.common.block.model.IModel;
import helporme.armsforge.common.block.model.ModelSuite;

import java.util.HashSet;

public class ModelRegistry
{
    public static HashSet<ModelSuite> modelSuites = new HashSet<ModelSuite>();

    public static void createDefaultModelSuites()
    {
        addModelSuiteFrom(new BlockMasterAnvil());
    }

    public static void addModelSuiteFrom(IModel model)
    {
        modelSuites.add(model.getModelSuite());
    }
}
