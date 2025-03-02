import org.junit.Before;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Test;
import praktikum.Database;
import java.util.List;
import static org.junit.Assert.*;

public class IngredientTypeJUnitTest {

    private Database database;
    private List<Ingredient> availableIngredients;

    @Before
    public void setUp() {
        database = new Database();
        availableIngredients = database.availableIngredients();
    }

    //проверяем, что перечисление содержит тип SAUCE
    @Test
    public void testIngredientTypeContainsSauce() {
        boolean containsSauce = availableIngredients.stream()
                .anyMatch(ingredient -> ingredient.getType() == IngredientType.SAUCE);
        assertTrue("В списке должен быть тип ингридиента SAUCE", containsSauce);
    }

    //проверяем, что перечисление содержит тип FILLING
    @Test
    public void testIngredientTypeContainsFilling() {
        boolean containsFilling = availableIngredients.stream()
                .anyMatch(ingredient -> ingredient.getType() == IngredientType.FILLING);
        assertTrue("В списке должен быть тип ингридиента FILLING", containsFilling);
    }

    //проверяем, что у ингредиентов с типом SAUCE правильно установлен тип
    @Test
    public void testIngredientTypeSauce() {
        for (Ingredient ingredient : availableIngredients) {
            if (ingredient.getType() == IngredientType.SAUCE) {
                assertEquals("Тип ингридиента соус должен быть SAUCE", IngredientType.SAUCE, ingredient.getType());
            }
        }
    }

    //проверяем, что у ингредиентов с типом FILLING правильно установлен тип
    @Test
    public void testIngredientTypeFilling() {
        for (Ingredient ingredient : availableIngredients) {
            if (ingredient.getType() == IngredientType.FILLING) {
                assertEquals("Тип ингридиента начинка должен быть FILLING", IngredientType.FILLING, ingredient.getType());
            }
        }
    }
}
