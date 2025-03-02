import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Database;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BunJUnitTest {
    private Database database;
    private Bun bun;

    @Before
    public void setUp() {
        database = Mockito.mock(Database.class);
        List<Bun> mockBuns = Arrays.asList(
                new Bun("black bun", 100.0f),
                new Bun("white bun", 200.0f),
                new Bun("red bun", 300.0f)
        );

        when(database.availableBuns()).thenReturn(mockBuns);
        bun = new Bun("Булочка", 50.5f);
    }

    //проверяем, что есть булочка "black bun"
    @Test
    public void testDatabaseContainsBlackBun() {
        assertTrue(database.availableBuns().stream().anyMatch(bun -> "black bun".equals(bun.getName())));
    }

    //проверяем, что есть булочка "white bun"
    @Test
    public void testDatabaseContainsWhiteBun() {
        assertTrue(database.availableBuns().stream().anyMatch(bun -> "white bun".equals(bun.getName())));
    }

    //проверяем, что есть булочка "red bun"
    @Test
    public void testDatabaseContainsRedBun() {
        assertTrue(database.availableBuns().stream().anyMatch(bun -> "red bun".equals(bun.getName())));
    }

    //проверяем цену черной булочки
    @Test
    public void testDatabaseBlackBunPrice() {
        assertEquals(100.0f, database.availableBuns().stream().filter(bun -> "black bun".equals(bun.getName())).findFirst().get().getPrice(), 0.001);
    }

    //проверяем цену белой булочки
    @Test
    public void testDatabaseWhiteBunPrice() {
        assertEquals(200.0f, database.availableBuns().stream().filter(bun -> "white bun".equals(bun.getName())).findFirst().get().getPrice(), 0.001);
    }

    //проверяем цену красной булочки
    @Test
    public void testDatabaseRedBunPrice() {
        assertEquals(300.0f, database.availableBuns().stream().filter(bun -> "red bun".equals(bun.getName())).findFirst().get().getPrice(), 0.001);
    }

    //проверяем, что булочки нет "blue bun"
    @Test
    public void testDatabaseNotContainsBlueBun() {
        assertFalse(database.availableBuns().stream().anyMatch(bun -> "blue bun".equals(bun.getName())));
    }

    //проверяем модель создания булочки - получение названия
    @Test
    public void testBlackBunName() {
        assertEquals("Булочка", bun.getName());
    }

    //проверяем модель создания булочки - получение цены
    @Test
    public void testBlackBunPrice() {
        assertEquals(50.5f, bun.getPrice(), 0.001);
    }
}
