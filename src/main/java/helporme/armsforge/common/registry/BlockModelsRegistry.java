package helporme.armsforge.common.registry;

import helporme.armsforge.common.models.IBlockModelContainer;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.models.BlockModelSuite;
import net.minecraft.block.Block;

import java.util.HashSet;

public final class BlockModelsRegistry
{
    private static final HashSet<BlockModelSuite> modelSuites = new HashSet<>();

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

    public static BlockModelSuite getModelSuiteFromContainer(IBlockModelContainer model)
    {
        ModelInfo modelInfo = model.getModelInfo();
        return new BlockModelSuite(
                model.getBlock(), model.getTileClass(), model.getTileRenderer(modelInfo),
                model.getItemRenderer(modelInfo), modelInfo);
    }

    public static void addModelSuite(BlockModelSuite modelSuite)
    {
        modelSuites.add(modelSuite);
    }

    public static Iterable<BlockModelSuite> getAllModelSuites()
    {
        return new HashSet<>(modelSuites);
    }
}
