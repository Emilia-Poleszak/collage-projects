/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class Customer {
    private final double id;
    Basket basket = new Basket();

    public Customer(double id){this.id = id;}

    public void pay() {
        System.out.println("Customer id " + this.id);
        System.out.println("Total is " + this.basket.getTotalPrice());
        this.basket.unpack();
        System.out.println("Bill is paid. Thank You!");
    }
}
