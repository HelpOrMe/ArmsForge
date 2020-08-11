package helporme.armsforge.api.blocks.tiles.table;

import helporme.armsforge.api.items.HammerType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICraftingTable extends ITable, IRecipeContainer
{
    CraftingTableType getTableType();

    void onHammerBlow(ItemStack hammerStack, EntityPlayer player);

    boolean isCraftActive();

    HammerType getNeededHammerType();

    float getMaxTime();

    float getTimeLeft();

    void cancelCraft();

    ISupportTable[] getSupportTablesNear();
}
