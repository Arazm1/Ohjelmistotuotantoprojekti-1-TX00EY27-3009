package fuelcon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuelCalculationTest {

    @Test
    public void testTotalFuelCalculation() {
        double distance = 500;
        double consumption = 8;
        double totalFuel = (consumption / 100) * distance;
        assertEquals(40.0, totalFuel, 0.001);
    }

    @Test
    public void testTotalCostCalculation() {
        double totalFuel = 40.0;
        double price = 1.5;
        double totalCost = totalFuel * price;
        assertEquals(60.0, totalCost, 0.001);
    }
}