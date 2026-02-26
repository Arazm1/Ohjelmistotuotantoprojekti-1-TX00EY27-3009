import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double a = input.nextDouble();
        System.out.print("Enter second number: ");
        double b = input.nextDouble();
        System.out.println("Sum: "+addMe(a, b));
        System.out.println("Subtraction: "+subMe(a, b));
    }

    public static double addMe(double a, double b){
        return a+b;
    }
    public static double subMe(double a, double b){
        return a-b;
    }
}