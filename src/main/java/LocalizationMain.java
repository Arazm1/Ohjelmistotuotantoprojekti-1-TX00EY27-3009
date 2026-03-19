import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocalizationMain {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Locale locale;

        ShoppingCart shoppingCart = new ShoppingCart();

        double numOfItems;

        System.out.println("Select a language");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");
        System.out.println("4. Japanese");
        int choice = scanner.nextInt();

        switch(choice){
            case 1 -> locale = new Locale("en", "US");
            case 2 -> locale = new Locale("fi", "FI");
            case 3 -> locale = new Locale("sv", "SE");
            case 4 -> locale = new Locale("ja", "JP");
            default -> {
                locale = new Locale("en", "US");
            }
        }

        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", locale);

        //Number of Items:
        System.out.println(rb.getString("enter.num.items"));
        numOfItems = scanner.nextInt();

        for(int i=0; i<numOfItems; i++){
            System.out.println(rb.getString("enter.price"));
            double itemPrice = scanner.nextDouble();

            System.out.println(rb.getString("enter.quantity"));
            int itemQuantity = scanner.nextInt();

            shoppingCart.addItemToCart(new ShoppingCartItem(itemPrice, itemQuantity));
        }

        System.out.println(rb.getString("total.cost") + " " + shoppingCart.getTotalCost());
    }
}
