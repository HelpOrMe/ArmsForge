package helporme.armsforge.client.registry;

import helporme.armsforge.common.models.BlockModelSuite;
import helporme.armsforge.common.models.IItemModelContainer;
import helporme.armsforge.common.registry.BlockModelsRegistry;
import helporme.armsforge.common.registry.ItemsRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public final class ItemRendererRegistry
{
    public static void registerFromItems()
    {
        for (Item item : ItemsRegistry.getAllItems())
        {
            if (item instanceof IItemModelContainer)
            {
                IItemModelContainer container = (IItemModelContainer)item;
                register(item, container.getItemRenderer(container.getModelInfo()));
            }
        }
    }

    public static void registerFromBlocks()
    {
        for (BlockModelSuite modelSuite : BlockModelsRegistry.getAllModelSuites())
        {
            Item item = Item.getItemFromBlock(modelSuite.block);
            register(item, modelSuite.itemRenderer);
        }
    }

    public static void register(Item item, IItemRenderer renderer)
    {
        MinecraftForgeClient.registerItemRenderer(item, renderer);
    }
}
