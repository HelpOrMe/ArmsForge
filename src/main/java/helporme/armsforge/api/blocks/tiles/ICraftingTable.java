package helporme.armsforge.api.blocks.tiles;

import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.recipes.hammer.HammerBlowPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICraftingTable extends ITable, IRecipeContainer
{
    CraftingTableType getTableType();

    void onHammerBlow(ItemStack hammerStack, EntityPlayer player);

    boolean isCraftActive();

    HammerType getHammerType();

    float getMaxTime();

    float getTimeLeft();

    void cancelCraft();

    ISupportTable[] getSupportTablesNear();
}
