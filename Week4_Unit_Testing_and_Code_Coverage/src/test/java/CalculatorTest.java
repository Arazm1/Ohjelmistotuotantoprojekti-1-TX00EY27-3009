// java.laskin;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
/*
 * JUnit 5
 */

public class CalculatorTest { // The class name must end with Test

    // Fixture: all tests use the same calculator,
    // which is reset before each test.
    private Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeEach
    public void clearCalculator() {
        calculator.reset();
    }

    // The test method name can be anything, as long as it has the @Test annotation
    @Test
    public void testAdd() {
        calculator.add(1);
        calculator.add(2);
        assertEquals(3, calculator.getResult(), "The sum of numbers 1 and 2 is incorrect");
    }

    @Test
    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(2);
        assertEquals(8, calculator.getResult(), "The difference between 10 and 2 is incorrect");
    }

    @Test
    @DisplayName("Test division 8 / 2")
    public void testDivide() {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult(), "Division 8/2 is incorrect");
    }

    // The correct result of this test is that division by zero throws an exception,
    // the caller then handles it in the desired way
    @Test
    @DisplayName("Test division by zero")
    public void testDivideByZero() {
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.divide(0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    //@Disabled("NOTE – The multiply() method is not implemented yet and should not be run")
    @DisplayName("Test multiply 3 * 3")
    public void testMultiply() {
        //fail("TEST HAS NOT BEEN IMPLEMENTED YET");
        calculator.add(3);
        calculator.multiply(3);
        assertEquals(9, calculator.getResult(), "Multiplication 3 * 3 is incorrect");
    }
}
