package helporme.armsforge.common.items.base;

import helporme.armsforge.client.render.items.ItemWeaponRenderer;
import helporme.armsforge.forge.wrapper.render.models.IItemModelContainer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.forge.wrapper.items.ItemSword;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;

public class ItemModelSword extends ItemSword implements IItemModelContainer
{
    protected String atlasTextureStr;

    public ItemModelSword(String name, Item.ToolMaterial material)
    {
        this(name, material, "items/weapons/SwordsAtlas");
    }

    public ItemModelSword(String name, Item.ToolMaterial material, String atlasTextureStr)
    {
        super(name, material);
        this.atlasTextureStr = atlasTextureStr;
    }

    @Override
    public IItemRenderer getItemRenderer(ModelInfo modelInfo)
    {
        return new ItemWeaponRenderer(modelInfo);
    }

    @Override
    public ModelInfo getModelInfo()
    {
        String itemPath = "items/weapons/" + name;
        return new ModelInfo(atlasTextureStr + ".png", itemPath + ".obj");
    }
}
