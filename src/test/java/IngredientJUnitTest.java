import org.junit.Before;
import org.junit.Test;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.*;
import java.util.List;


public class IngredientJUnitTest {
    private Database database;
    private List<Ingredient> availableIngredients;

    @Before
    public void setUp() {
        database = new Database();
        availableIngredients = database.availableIngredients();
    }

    //проверяем метод getName() первого ингредиента из списка "hot sauce"
    @Test
    public void testGetName() {
        Ingredient ingredient = availableIngredients.get(0);
        assertEquals("hot sauce", ingredient.getName());
    }

    //проверяем метод getPrice() второго ингредиента из списка
    @Test
    public void testGetPrice() {
        Ingredient ingredient = availableIngredients.get(1);
        assertEquals(200.0f, ingredient.getPrice(), 0.001f);
    }

    //проверяем метод getType() третьего ингредиента из списка (соус)
    @Test
    public void testGetType_Sauce() {
        Ingredient ingredient = availableIngredients.get(0);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    //проверяем метод getType() четвертого ингредиента из списка (начинка)
    @Test
    public void testGetType_Filling() {
        Ingredient ingredient = availableIngredients.get(3);
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}
