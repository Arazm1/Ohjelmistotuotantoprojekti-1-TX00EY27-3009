import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    @Test
    public void testAddMe() {
        assertEquals(5.0, Calculator.addMe(2.0, 3.0));
        assertEquals(0.0, Calculator.addMe(-2.0, 2.0));
        assertEquals(-5.0, Calculator.addMe(-2.0, -3.0));
    }

    @Test
    public void testSubMe() {
        assertEquals(1.0, Calculator.subMe(3.0, 2.0));
        assertEquals(-4.0, Calculator.subMe(-2.0, 2.0));
        assertEquals(1.0, Calculator.subMe(-2.0, -3.0));
    }
}