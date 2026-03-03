import java.math.BigDecimal;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class ProductInBasket extends Product{
    private final double numberOfProducts;

    public ProductInBasket(String name, BigDecimal price, int number){
        this.setName(name);
        this.setPrice(price);
        this.numberOfProducts = number;
    }
}
