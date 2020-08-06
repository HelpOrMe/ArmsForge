package helporme.armsforge.common.tiles.base;

import helporme.armsforge.api.blocks.tables.CraftingTableType;
import helporme.armsforge.api.items.HammerType;
import helporme.armsforge.api.items.IHammer;
import helporme.armsforge.api.items.IItemRecipe;
import helporme.armsforge.api.recipes.ICraftingTableRecipe;
import helporme.armsforge.api.recipes.table.TablesShape;
import helporme.armsforge.api.utils.Vector3Int;
import helporme.armsforge.api.blocks.tables.ICraftingTable;
import helporme.armsforge.api.blocks.tables.ISupportTable;
import net.minecraft.item.ItemStack;

import java.util.*;


// Логика работы стола:
// 1.   При ударе молотка проверяем можно ли запустить крафт
// 1.1  Проверяем запущен ли уже крафт
// 1.2  Проверяем есть ли предмет рецепта на столе
// ins! Проверяем форму столов с нужной в крафте
// 1.3  Если крафт можно запустить - запускаем.
// 1.4  Выбираем рецепт из предмета рецепта
// 1.5  Берем ингредиенты с рецепта
// 1.6  Сохраняем в кеш позиции столов вокруг с ингредиентами
// 1.7  Случайно выбираем индекс ингредиент
// 1.8  Случайно выбираем тип удара молотком для ингредиента из предоставленных ArmsForgeApi
//      и выставляем время для удара

// 2.   Если крафт запущен, каждый тик отнимаем с времени для удара 1/20 секунды.
// 2.2  Если время вышло - прекращаем крафт

// 3.   Обрабатываем удар молотком
// 3.1  Сверяем тип удара молотка с выбранным типом удара молотка по предмету ранее и
//      проверяем не вышло ли время
// 3.11 Если тип удара неправильный или время вышло - прекращаем крафт
// 3.2  Берем позицию стола с кеша и проверяем есть ли на позиции стол и валидны ли на нем предметы
// 3.21 Если что-то пошло не так в пункте 2.3 - начинаем пере-поиск столов вокруг и обновляем кеш.
// 3.22 Если до сих пор предмета нет - прекращаем крафт
// 3.3  Убираем ингредиент со стола (вычитаем нужное кол-во предметов указанное в ингредиенте)
// 3.4  Убираем стол из кеша и ингредиент из списка.
// 3.5  Добавляем индекс ингредиента в список обработанных
// 3.6  Проверяем, существуют ли ещё ингредиенты
// 3.61 Если да, повторяем 1.7
// 3.7  Если нет, заканчиваем крафт и меняем предмет на главном столе предмет на результат крафта
// 3.8  Очищаем информацию о состоянии крафта

public abstract class TileEntityCraftingTable extends TileEntityTable implements ICraftingTable
{
    // TODO: Config
    private static final int radius = 9;
    protected static final Random random = new Random();

    protected boolean craftActive = false;
    protected ICraftingTableRecipe selectedRecipe;

    protected Map<String, Vector3Int> cachedTablesPositionsByIngredients;
    protected ISupportTable[] supportTables;
    protected ItemStack[] ingredients;
    protected int selectedIngredientIndex = 0;

    protected HammerType neededHammerType;
    protected float timeLeft;

    @Override
    public void onHammerBlow(IHammer hammer)
    {
        if (canActivateCraft())
        {
            activateCraft();
        }
        else
        {
            handleHammerBlow(hammer);
        }
    }

    public boolean canActivateCraft()
    {
        return !isCraftActive() && hasRecipe() && isTablesShapeValid();
    }

    @Override
    public boolean isCraftActive()
    {
        return craftActive;
    }

    public boolean isTablesShapeValid()
    {
        if (hasRecipe())
        {
            ICraftingTableRecipe recipe = getRecipe();
            TablesShape tablesShape = new TablesShape(this, getSupportTablesNear());
            return recipe.isTablesShapeValid(tablesShape);
        }
        return false;
    }

    @Override
    public void activateCraft() {

    }

    @Override
    public void selectRecipe(ICraftingTableRecipe recipe) {

    }

    @Override
    public ISupportTable[] getSupportTablesNear() {
        return new ISupportTable[0];
    }


    @Override
    public boolean hasRecipe()
    {
        return isEmptyAt(1);
    }

    @Override
    public IItemRecipe getRecipe()
    {
        return (IItemRecipe)getStackInSlot(1).getItem();
    }

    @Override
    public void setRecipe(ItemStack recipe)
    {
        if (isRecipeValidForThisTable(recipe))
        {
            setInventorySlotContents(1, recipe);
            return;
        }
        throw new IllegalArgumentException("Invalid recipe type " + recipe.getItem().getClass());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IItemRecipe || slot == 1)
        {
            return isRecipeValidForThisTable(itemStack) && slot == 1;
        }
        return true;
    }

    protected boolean isRecipeValidForThisTable(ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IItemRecipe)
        {
            IItemRecipe recipeItem = (IItemRecipe)itemStack.getItem();
            ICraftingTableRecipe recipe = recipeItem.getRecipe(itemStack);
            CraftingTableType recipeCraftingTable = recipe.getCraftingTableType();
            return recipeCraftingTable.equals(getTableType());
        }
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return 2;
    }
}
