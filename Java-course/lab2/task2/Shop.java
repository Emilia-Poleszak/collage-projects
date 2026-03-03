import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class Shop {
    public static void main(String[] args) {
        Product bread = new Product("Bread", 2.50);
        Product milk = new Product("Milk", 2.99);
        Product apple = new Product("Apple", 0.39);
        Product egg = new Product("Egg", 0.79);
        Product chips = new Product("Chips", 6.99);
        Product chocolate = new Product("Chocolate", 5.99);
        Product cheese = new Product("Cheese", 3.49);
        Product soap = new Product("Soap", 3.49);
        Product apple_juice = new Product("Apple juice", 4.99);
        Product peanut_butter = new Product("Peanut butter", 6.99);
        Product flour = new Product("flour", 2.49);
        Product salt = new Product("Salt", 1.99);
        Product yogurt = new Product("Yogurt", 2.49);
        Product tomato = new Product("Tomato", 0.39);
        Product carrot = new Product("Carrot", 0.19);
        Product onion = new Product("Onion", 0.19);

        List<Product> products = new ArrayList<>();
        products.add(bread);
        products.add(milk);
        products.add(apple);
        products.add(egg);
        products.add(chips);
        products.add(chocolate);
        products.add(cheese);
        products.add(soap);
        products.add(onion);
        products.add(flour);
        products.add(carrot);
        products.add(tomato);
        products.add(yogurt);
        products.add(salt);
        products.add(peanut_butter);
        products.add(apple_juice);

        System.out.println("Shop 'U Anny i Emilii' has " + products.size() + " products in sale");

        Customer anna = new Customer(1);
        Customer emilia = new Customer(2);
        Customer mateusz = new Customer(3);
        Customer wojciech = new Customer(4);
        Customer jan = new Customer(5);
        Customer alicja = new Customer(6);
        Customer grzegorz = new Customer(7);
        Customer alex = new Customer(8);
        Customer julia = new Customer(9);
        Customer natalia = new Customer(10);
        Customer zofia = new Customer(11);
        Customer tomasz = new Customer(12);

        List<Customer> customers = new ArrayList<>();
        customers.add(anna);
        customers.add(emilia);
        customers.add(mateusz);
        customers.add(wojciech);
        customers.add(jan);
        customers.add(alex);
        customers.add(alicja);
        customers.add(grzegorz);
        customers.add(julia);
        customers.add(natalia);
        customers.add(zofia);
        customers.add(tomasz);

        System.out.println("Today " + customers.size() + " customers visited the shop");

        anna.basket.addToBasket(bread, 2);
        anna.basket.addToBasket(apple, 6);
        anna.basket.addToBasket(egg, 10);
        emilia.basket.addToBasket(milk, 2);
        emilia.basket.addToBasket(chocolate, 1);
        emilia.basket.addToBasket(soap, 1);
        mateusz.basket.addToBasket(chips, 3);
        mateusz.basket.addToBasket(chocolate, 2);
        wojciech.basket.addToBasket(cheese, 2);
        wojciech.basket.addToBasket(chips, 3);
        jan.basket.addToBasket(apple_juice, 2);
        natalia.basket.addToBasket(egg, 20);
        natalia.basket.addToBasket(flour, 1);
        natalia.basket.addToBasket(chocolate, 3);
        alicja.basket.addToBasket(tomato, 15);
        grzegorz.basket.addToBasket(chips, 1);
        grzegorz.basket.addToBasket(apple_juice, 1);
        alex.basket.addToBasket(soap, 1);
        alex.basket.addToBasket(salt, 2);
        julia.basket.addToBasket(carrot, 5);
        julia.basket.addToBasket(tomato, 3);
        zofia.basket.addToBasket(onion, 2);
        zofia.basket.addToBasket(peanut_butter, 1);
        tomasz.basket.addToBasket(bread, 1);
        tomasz.basket.addToBasket(cheese, 3);
        tomasz.basket.addToBasket(tomato, 3);

        Queue<Customer> queue = new LinkedList<>();
        queue.add(anna);
        queue.add(jan);
        queue.add(emilia);
        queue.add(wojciech);
        queue.add(zofia);
        queue.add(mateusz);
        queue.add(alex);
        queue.add(alicja);
        queue.add(tomasz);
        queue.add(julia);
        queue.add(grzegorz);
        queue.add(natalia);

        while (!queue.isEmpty()) {
            queue.poll().pay();
        }

        System.out.println("All customers were served. Good bye!");
    }
}
