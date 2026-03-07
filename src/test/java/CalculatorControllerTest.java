import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorControllerTest {

    private final CalculatorController controller = new CalculatorController();

    @Test
    void testAdd() {
        assertEquals(5.0, controller.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2.0, controller.subtract(5, 3));
    }

    @Test
    void testProduct() {
        assertEquals(6.0, controller.product(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(2.0, controller.division(6, 3));
        assertEquals(Double.POSITIVE_INFINITY, controller.division(1, 0)); // or NaN handling if you modify division method
    }
}