import java.math.BigDecimal;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class Product {
    private String name;
    private BigDecimal price;

    public Product(){}

    public Product(String name, double price){
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public BigDecimal getPrice() { return price; }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public void setPrice(BigDecimal price) {this.price = price;}
}
