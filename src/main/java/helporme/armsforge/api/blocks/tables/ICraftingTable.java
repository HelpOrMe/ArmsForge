package helporme.armsforge.api.blocks.tables;

import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Set;

public interface ICraftingTable extends ITable, IRecipeContainer
{
    CraftingTableType getTableType();

    void onHammerBlow(IHammer hammer, EntityPlayer player);

    boolean isCraftActive();

    ISupportTable[] getSupportTablesNear();
}
