package helporme.armsforge.common.items.base;

import helporme.armsforge.client.render.items.ItemWeaponRenderer;
import helporme.armsforge.forge.wrapper.models.IItemModelContainer;
import helporme.armsforge.forge.wrapper.models.ModelInfo;
import helporme.armsforge.forge.wrapper.items.ItemSword;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;

public class ItemModelSword extends ItemSword implements IItemModelContainer
{
    public ItemModelSword(String name, Item.ToolMaterial material)
    {
        super(name, material);
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
        return new ModelInfo(itemPath + ".png", itemPath + ".obj");
    }
}
