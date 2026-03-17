import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocalizationTest {
    public static void main(String[] args){
        /*
        String language = "fa";
        String Country = "IR";
        Locale myLocale = new Locale(language, Country);
        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", myLocale);
        String terve = rb.getString("wish");
        System.out.println(terve);
         */



        Scanner input = new Scanner(System.in);
        System.out.println("Select a language");
        System.out.println("1. English");
        System.out.println("2. Farsi");
        System.out.println("3. Suomi");

        int choice = input.nextInt();
        Locale locale;
        switch(choice){
            case 1 -> locale = new Locale("en", "UK");
            case 2 -> locale = new Locale("fa", "IR");
            case 3 -> locale = new Locale("fi", "FI");
            default -> {
                locale = new Locale("en", "UK");
            }
        }


        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);
        System.out.println(rb.getString("prompt1"));
        double a = input.nextDouble();
        System.out.println(rb.getString("prompt2"));
        double b = input.nextDouble();

        double summa = addMe(a, b);
        double minus = subMe(a, b);
        System.out.println(rb.getString("sum")+ " "+summa);
        System.out.println(rb.getString("subtract")+ " "+minus);



    }

    public static double addMe(double a,double b){
        return a+b;
    }
    public static double subMe(double a,double b){
        return a-b;

    }
}
