public class TemperatureConverter {

    public double fahrenheitToCelsius(double inputFahrenheit){
        double resultInCelsius = (inputFahrenheit - 32) * 5 / 9;
        return resultInCelsius;
    }

    public double celsiusToFahrenheit(double inputCelsius){
        double resultInFahrenheit = (inputCelsius * 9 / 5 + 32);
        return  resultInFahrenheit;
    }

    public boolean isExtremeTemperature(double inputCelsius){
        if(inputCelsius < -40 || inputCelsius>50){
            return true;
        }
        else{
            return false;
        }
    }
}
