package helporme.armsforge.api.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICraftingTable extends ITable, IRecipeContainer
{
    CraftingTableType getTableType();

    void onHammerBlow(ItemStack hammerStack, EntityPlayer player);

    boolean isCraftActive();

    void cancelCraft();

    ISupportTable[] getSupportTablesNear();
}
