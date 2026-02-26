public class TemperatureConverter {
    public static void main(String[] args) {

        fahrenheitToCelsius(20.2);

        celsiusToFahrenheit(20.2);

        isExtremeTemperature(51);

        kelvinToCelsius(30.3);

    }



    public static double fahrenheitToCelsius(double inputFahrenheit){
        double resultInCelsius = (inputFahrenheit - 32) * 5 / 9;
        return resultInCelsius;
    }

    public static double celsiusToFahrenheit(double inputCelsius){
        double resultInFahrenheit = (inputCelsius * 9 / 5 + 32);
        return  resultInFahrenheit;
    }

    public static boolean isExtremeTemperature(double inputCelsius){
        if(inputCelsius < -40 || inputCelsius>50){
            return true;
        }
        else{
            return false;
        }
    }

    public static double kelvinToCelsius(double inputKelvin){
        return inputKelvin - 273.15;
    }
}
