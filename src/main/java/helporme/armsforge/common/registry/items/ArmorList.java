package helporme.armsforge.common.registry.items;

import helporme.armsforge.common.items.armor.*;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Constructor;

public class ArmorList extends ItemList
{
    @Override
    public void addDefault()
    {
        addArmorItems(
                UnderArmor.class, SandArmor.class,
                HeavySegmantaArmor.class, LorikaHamataArmor.class);
        addArmorItem(SegmantaArmor.class, 0, 1);
    }

    public void addArmorItems(Class<?>... armorClasses)
    {
        for (Class<?> armorCls : armorClasses)
        {
            addArmorItem(armorCls);
        }
    }

    public void addArmorItem(Class<?> armorCls, int... armorTypes)
    {
        if (armorTypes.length == 0)
        {
            armorTypes = new int[] {0, 1, 2, 3};
        }
        try
        {
            Constructor<?> constructor = armorCls.getConstructors()[0];
            for (int armorType : armorTypes)
            {
                addItem((Item)constructor.newInstance(armorType == 2 ? 3 : 4, armorType));
            }
        }
        catch (ReflectiveOperationException e)
        {
            LogManager.getLogger().error("Unable to register armor item: " + armorCls.getName(), e);
        }
    }
}
