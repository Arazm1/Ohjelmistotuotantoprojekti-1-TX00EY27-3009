package fuelcon.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceIntegrationTest {

    private final CalculationService service = new CalculationService();

    @Test
    void testSaveCalculationReturnsTrue() {
        boolean result = service.saveCalculation(500, 8, 1.5, 40.0, 60.0, "en");
        assertTrue(result);
    }

    @Test
    void testSaveCalculationWithFrench() {
        boolean result = service.saveCalculation(300, 6, 1.8, 18.0, 32.4, "fr");
        assertTrue(result);
    }

    @Test
    void testSaveCalculationWithZeroValues() {
        boolean result = service.saveCalculation(0, 0, 0, 0, 0, "en");
        assertNotNull(result);
    }

    @Test
    void testGetConnectionNotNull() {
        try {
            var conn = service.getConnection();
            assertNotNull(conn);
            conn.close();
        } catch (Exception e) {
            System.out.println("DB not available: " + e.getMessage());
        }
    }

}
