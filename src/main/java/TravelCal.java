public class TravelCal {

    public static void main(String[] args) {

        TemperatureConverter temperatureConverter = new TemperatureConverter();

        temperatureConverter.fahrenheitToCelsius(20.2);

        temperatureConverter.celsiusToFahrenheit(20.2);

        temperatureConverter.isExtremeTemperature(51);

        temperatureConverter.kelvinToCelsius(30.3);
    }
}
