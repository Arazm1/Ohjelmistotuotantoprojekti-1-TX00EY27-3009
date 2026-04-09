package fuelcon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelCalculatorTest {
    FuelCalculator calculator = new FuelCalculator();
    double delta = 0.001;

    @Test
    public void testTotalFuelCalculation() {
        double result = calculator.calculateTotalFuel(500, 8);
        assertEquals(40.0, result, delta);
    }

    @Test
    public void testTotalCostCalculation() {
        double result = calculator.calculateTotalCost(40.0, 1.5);
        assertEquals(60.0, result, delta);
    }

    @Test
    public void testZeroDistance() {
        assertEquals(0.0, calculator.calculateTotalFuel(0, 8), delta);
    }

    @Test
    public void testZeroCost() {
        assertEquals(0.0, calculator.calculateTotalCost(40.0, 0), delta);
    }

    @Test
    public void testHighConsumption() {
        assertEquals(150.0, calculator.calculateTotalFuel(500, 30), delta);
    }
}
