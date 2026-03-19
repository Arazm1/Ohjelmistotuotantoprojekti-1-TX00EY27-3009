import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void testEmptyCartTotal() {
        assertEquals(0.0, cart.getTotalCost());
    }

    @Test
    void testAddSingleItem() {
        cart.addItemToCart(new ShoppingCartItem(10.0, 2));
        assertEquals(20.0, cart.getTotalCost());
    }

    @Test
    void testAddMultipleItems() {
        cart.addItemToCart(new ShoppingCartItem(10.0, 2));  // 20.0
        cart.addItemToCart(new ShoppingCartItem(5.0, 3));   // 15.0
        cart.addItemToCart(new ShoppingCartItem(1.0, 10));  // 10.0
        assertEquals(45.0, cart.getTotalCost());
    }

    @Test
    void testAddItemWithDecimalPrice() {
        cart.addItemToCart(new ShoppingCartItem(2.50, 4));  // 10.0
        cart.addItemToCart(new ShoppingCartItem(1.99, 2));  // 3.98
        assertEquals(13.98, cart.getTotalCost(), 0.001);
    }

    @Test
    void testAddItemWithZeroPrice() {
        cart.addItemToCart(new ShoppingCartItem(0.0, 5));
        assertEquals(0.0, cart.getTotalCost());
    }

    @Test
    void testAddItemWithZeroQuantity() {
        cart.addItemToCart(new ShoppingCartItem(10.0, 0));
        assertEquals(0.0, cart.getTotalCost());
    }
}