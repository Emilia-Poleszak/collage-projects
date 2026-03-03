import java.math.BigDecimal;
import java.util.Stack;


/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class Basket {
    private BigDecimal totalPrice = new BigDecimal("0");
    private final Stack<Product> products = new Stack<>();

    public void setTotalPrice(BigDecimal totalPrice) {this.totalPrice = totalPrice;}

    public BigDecimal getTotalPrice() { return totalPrice;}

    public void addToBasket(Product p, int number){
        ProductInBasket productInBasket = new ProductInBasket(p.getName(), p.getPrice(), number);
        products.push(productInBasket);
        BigDecimal newTotalPrice = totalPrice.add(p.getPrice().multiply(BigDecimal.valueOf(number)));
        this.setTotalPrice(newTotalPrice);
    }

    public void unpack() {
        while (!products.isEmpty()){
            System.out.println(products.firstElement().getName());
            products.remove(0);
        }
    }
}
