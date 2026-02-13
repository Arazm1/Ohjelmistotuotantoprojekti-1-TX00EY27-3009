
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ExtraTest extends AbstractParent {

    private static Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testPowerOn() {
        System.out.println("@BeforeAll Power ON (before the first test)");
        calculator.powerOn();
    }

    @AfterAll
    public static void testPowerOff() {
        System.out.println("@AfterAll Power OFF (all tests executed).");
        calculator.powerOff();
        calculator = null;
    }

    @BeforeEach
    public void testReset() {
        System.out.println("  Reset calculator.");
        calculator.reset();
        assertEquals(0, calculator.getResult(), "Reset failed");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 5})
    public void testSquare(int n) {
        calculator.square(n);
        assertEquals(n*n, calculator.getResult(), DELTA,"Squaring number " + n + " is incorrect");
    }

    /*
    @Test
    public void testSquare2() {
        calculator.square(2);
        assertEquals(4, calculator.getResult(), "Squaring number 2 is incorrect");
    }

    @Test
    public void testSquare4() {
        calculator.square(4);
        assertEquals(16, calculator.getResult(), "Squaring number 4 is incorrect");
    }

    @Test
    public void testSquare5() {
        calculator.square(5);
        assertEquals(25, calculator.getResult(), DELTA, "Squaring number 5 is incorrect");
    }
    */

    @Test
    public void testSquareRoot2() {
        calculator.squareRoot(2);
        // Add assertXXX() here, expected result should be (int) Math.sqrt(2)
        assertEquals( Math.sqrt(2), calculator.getResult(), DELTA,"Square root of 2 is incorrect");
    }

    @Test
    @DisplayName("Test negative square root")
    public void testSquareRootNegative() {
        //fail("TEST HAS NOT BEEN IMPLEMENTED YET.");
        assertThrows(ArithmeticException.class, () -> calculator.squareRoot(-1), "Square root of negative number -1 should throw exception");
    }
}
