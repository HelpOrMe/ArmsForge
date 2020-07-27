package helporme.armsforge.client.render.item.registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.common.block.model.ModelSuite;
import helporme.armsforge.common.block.model.registry.ModelsRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ItemRendererRegistry
{
    public static void registerItemRenderersFromModelSuites()
    {
        for (ModelSuite modelSuite : ModelsRegistry.getAllModelSuites())
        {
            Item item = Item.getItemFromBlock(modelSuite.block);
            registerItemRenderer(item, modelSuite.itemRenderer);
        }
    }

    public static void registerItemRenderer(Item item, IItemRenderer renderer)
    {
        MinecraftForgeClient.registerItemRenderer(item, renderer);
    }
}
