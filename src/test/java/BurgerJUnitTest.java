import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerJUnitTest {
    private Burger burger;
    private Database database;

    private Bun bun;
    private Ingredient ingredient;

    public BurgerJUnitTest(Bun bun, Ingredient ingredient) {
        this.bun = bun;
        this.ingredient = ingredient;
    }

    @Before
    public void setUp() {
        database = new Database();
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
    }

    // Параметризация: тесты для каждой булочки и ингредиента
    @Parameterized.Parameters
    public static Object[][] data() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();
        Object[][] data = new Object[buns.size() * ingredients.size()][2];
        int index = 0;
        for (Bun bun : buns) {
            for (Ingredient ingredient : ingredients) {
                data[index][0] = bun;
                data[index][1] = ingredient;
                index++;
            }
        }
        return data;
    }

    //проверяем установку булочки
    @Test
    public void testSetBuns() {
        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    //проверяем добавление ингредиента
    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient);
        assertTrue("Ингредиент должен быть добавлен", burger.ingredients.contains(ingredient));
    }

    //проверяем удаление ингредиента
    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.addIngredient(ingredient1); //добавляем второй ингредиент в бургер
        burger.removeIngredient(1); //удаляем добавленный ингредиент

        assertFalse("Ингредиент должен быть удален", burger.ingredients.contains(ingredient1));
    }

    //проверяем перемещение ингредиента
    @Test
    public void testMoveIngredient() {
        Ingredient anotherIngredient = new Ingredient(IngredientType.SAUCE, "sour cream", 200);
        burger.addIngredient(anotherIngredient); //добавляем ингридиент для расширения списка

        burger.moveIngredient(0, 1);  // Перемещаем ингредиент на другую позицию
        assertEquals("Ингредиент должен быть перемещен", ingredient, burger.ingredients.get(1));
    }

    //проверяем расчёт цены бургера
    @Test
    public void testGetPrice() {
        float expectedPrice = bun.getPrice() * 2 + ingredient.getPrice();
        assertEquals("Цена бургера должна быть правильной", expectedPrice, burger.getPrice(), 0.001f);
    }

    //проверям чек бургера
    @Test
    public void testGetReceipt() {
        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(), ingredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals("Чек должен быть правильным", expectedReceipt, burger.getReceipt());
    }
}
