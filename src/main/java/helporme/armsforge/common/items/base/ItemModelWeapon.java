package helporme.armsforge.common.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import helporme.armsforge.client.render.items.ItemWeaponRenderer;
import helporme.armsforge.common.core.Version;
import helporme.armsforge.forge.wrapper.render.models.IItemModelContainer;
import helporme.armsforge.forge.wrapper.render.models.ModelInfo;
import helporme.armsforge.forge.wrapper.items.base.ItemSword;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;

public class ItemModelWeapon extends ItemSword implements IItemModelContainer
{
    protected String atlasTextureStr;

    public ItemModelWeapon(String name, Item.ToolMaterial material, String atlasTextureStr)
    {
        super(name, material);
        this.atlasTextureStr = atlasTextureStr;
        setTextureName(Version.modid + ":" + "weapons/" + atlasTextureStr);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer(ModelInfo modelInfo)
    {
        return new ItemWeaponRenderer(modelInfo);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelInfo getModelInfo()
    {
        String itemPath = "items/weapons/" + name;
        String atlasPath = "items/weapons/" + atlasTextureStr;
        return new ModelInfo(atlasPath, itemPath);
    }
}
