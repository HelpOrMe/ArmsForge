package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityTableRenderer;
import helporme.armsforge.common.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.TileEntitySupportTable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntitySupportTableRenderer extends TileEntityTableRenderer
{
    protected final IModelCustom shelfModel;

    public TileEntitySupportTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
        ResourceLocation shelfModelLocation = new ResourceLocation(
                Version.modid, "models/blocks/SupportTableShelf.obj");
        shelfModel = AdvancedModelLoader.loadModel(shelfModelLocation);
    }

    @Override
    public void renderModel(TileEntity tile, float timeDelta)
    {
        TileEntitySupportTable supportTable = (TileEntitySupportTable)tile;
        if (supportTable.isShelf)
        {
            shelfModel.renderAll();
        }
        else
        {
            super.renderModel(tile, timeDelta);
        }
    }
}
