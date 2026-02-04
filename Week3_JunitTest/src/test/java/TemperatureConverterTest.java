import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    TemperatureConverter temperatureConverter = new TemperatureConverter();

    double allowedDifference = 0.01;

    @Test
    void testFahrenheitToCelsius(){
        double testResult = temperatureConverter.fahrenheitToCelsius(100);
        assertEquals(37.778, testResult, allowedDifference);
    }

    @Test
    void testCelsiusToFahrenheit(){
        double testResult = temperatureConverter.celsiusToFahrenheit(100);
        assertEquals(212, testResult, allowedDifference);
    }

    @Test
    void testExtremelyWarmTemperature(){
        assertTrue(temperatureConverter.isExtremeTemperature(51));
    }

    @Test
    void testExtremelyColdTemperature(){
        assertTrue(temperatureConverter.isExtremeTemperature(-41));
    }

    @Test
    void testNormalTemperature(){
        assertFalse(temperatureConverter.isExtremeTemperature(12));
    }

    //Test multiple fahrenheit -> celsius
    @Test
    void testFahrenheitToCelsiusMultipleValues(){
        double[] fahrenheitValues = {32, 68, 101.1, 95.6};
        double[] celsiusValues = {0, 20, 38.38, 35.33};

        for(int i = 0; i<fahrenheitValues.length; i++){
            double testResult = temperatureConverter.fahrenheitToCelsius(fahrenheitValues[i]);
            assertEquals(celsiusValues[i], testResult, allowedDifference);
        }
    }

    //Test multiple celsius -> fahrenheit
    @Test
    void testCelsiusToFahrenheitMultipleValues(){
        double[] fahrenheitValues = {32, 68, 101.08, 95.6, -32, -25.6};
        double[] celsiusValues = {0, 20, 38.38, 35.33, -35.55, -32};

        for(int i = 0; i<celsiusValues.length; i++){
            double testResult = temperatureConverter.celsiusToFahrenheit(celsiusValues[i]);
            assertEquals(fahrenheitValues[i], testResult, allowedDifference + 0.01);
        }
    }

    //Test boundaries
    @Test
    void testExtremeTemperatureBoundaries(){
        assertFalse(temperatureConverter.isExtremeTemperature(50));
        assertFalse(temperatureConverter.isExtremeTemperature(-40));

        assertTrue(temperatureConverter.isExtremeTemperature(50.1));
        assertTrue(temperatureConverter.isExtremeTemperature(-40.1));
    }

    //Test high number: 1*10^6
    @Test
    void testHighNumberCelsiusToFahrenheit(){
        double testResult = temperatureConverter.celsiusToFahrenheit(1e6);
        assertTrue(testResult>0);
    }

    @Test
    void testHighNumberFahrenheitToCelsius(){
        double testResult = temperatureConverter.fahrenheitToCelsius(-1e6);
        assertTrue(testResult<0);
    }



}