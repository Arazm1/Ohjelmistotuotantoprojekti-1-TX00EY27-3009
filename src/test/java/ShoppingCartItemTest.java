import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartItemTest {

    @Test
    void testCalculateTotal() {
        ShoppingCartItem item = new ShoppingCartItem(10.0, 3);
        assertEquals(30.0, item.calculateTotal());
    }

    @Test
    void testCalculateTotalWithDecimalPrice() {
        ShoppingCartItem item = new ShoppingCartItem(2.50, 4);
        assertEquals(10.0, item.calculateTotal());
    }

    @Test
    void testCalculateTotalWithZeroQuantity() {
        ShoppingCartItem item = new ShoppingCartItem(10.0, 0);
        assertEquals(0.0, item.calculateTotal());
    }

    @Test
    void testCalculateTotalWithZeroPrice() {
        ShoppingCartItem item = new ShoppingCartItem(0.0, 5);
        assertEquals(0.0, item.calculateTotal());
    }

    @Test
    void testSetItemPrice() {
        ShoppingCartItem item = new ShoppingCartItem(10.0, 2);
        item.setItemPrice(20.0);
        assertEquals(40.0, item.calculateTotal());
    }

    @Test
    void testSetItemQuantity() {
        ShoppingCartItem item = new ShoppingCartItem(10.0, 2);
        item.setItemQuantity(5);
        assertEquals(50.0, item.calculateTotal());
    }
}
