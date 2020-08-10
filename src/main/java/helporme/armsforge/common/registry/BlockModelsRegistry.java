package helporme.armsforge.common.registry;

import helporme.armsforge.forge.wrapper.render.models.IBlockModelContainer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.forge.wrapper.render.models.ModelBlockSuite;
import net.minecraft.block.Block;

import java.util.HashSet;

public final class BlockModelsRegistry
{
    private static final HashSet<ModelBlockSuite> modelSuites = new HashSet<>();

    public static void createBlockModelSuites()
    {
        for (Block block : BlocksRegistry.getAllBlocks())
        {
            if (block instanceof IBlockModelContainer)
            {
                addModelSuite(getModelSuiteFromContainer((IBlockModelContainer)block));
            }
        }
    }

    public static ModelBlockSuite getModelSuiteFromContainer(IBlockModelContainer model)
    {
        ModelInfo modelInfo = model.getModelInfo();
        return new ModelBlockSuite(
                model.getBlock(), model.getTileClass(), model.getTileRenderer(modelInfo),
                model.getItemRenderer(modelInfo), modelInfo);
    }

    public static void addModelSuite(ModelBlockSuite modelSuite)
    {
        modelSuites.add(modelSuite);
    }

    public static Iterable<ModelBlockSuite> getAllModelSuites()
    {
        return new HashSet<>(modelSuites);
    }
}
