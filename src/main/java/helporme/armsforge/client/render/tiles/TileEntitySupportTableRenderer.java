package helporme.armsforge.client.render.tiles;

import helporme.armsforge.client.render.tiles.base.TileEntityTableRenderer;
import helporme.armsforge.common.blocks.models.ModelInfo;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.common.tiles.TileEntitySupportTable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class TileEntitySupportTableRenderer extends TileEntityTableRenderer
{
    protected final IModelCustom defaultModel;
    protected final IModelCustom shelfModel;

    public TileEntitySupportTableRenderer(ModelInfo modelInfo)
    {
        super(modelInfo);
        defaultModel = model;
        ResourceLocation shelfModelLocation = new ResourceLocation(
                Version.modid, "models/SupportTableShelf.obj");
        shelfModel = AdvancedModelLoader.loadModel(shelfModelLocation);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeDelta)
    {
        TileEntitySupportTable supportTable = (TileEntitySupportTable)tile;
        setModelByShelfFlag(supportTable.isShelf);
        super.renderTileEntityAt(tile, x, y, z, timeDelta);
    }

    protected void setModelByShelfFlag(boolean isShelf)
    {
        if (isShelf)
        {
            model = shelfModel;
        }
        else
        {
            model = defaultModel;
        }
    }
}
