public class ShoppingCartItem {


    private double itemPrice;
    private int itemQuantity;


    public ShoppingCartItem(double itemPrice, int itemQuantity){
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }


    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity){
        this.itemQuantity = itemQuantity;
    }


    public double calculateTotal(){
        return this.itemPrice * this.itemQuantity;
    }
}
