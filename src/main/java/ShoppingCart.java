import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<ShoppingCartItem> items = new ArrayList<>();

    public ShoppingCart(){}

    public void addItemToCart(ShoppingCartItem shoppingCartItem){
        items.add(shoppingCartItem);
    }

    public double getTotalCost(){
        double total = 0;
        for(ShoppingCartItem item : items){
            total += item.calculateTotal();
        }
        return total;
    }
}
