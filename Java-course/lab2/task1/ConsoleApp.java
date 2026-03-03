/**
 * Exercise nr 1
 *
 * @author  Anna Czarkowska
 * @author  Emilia Poleszak
 */

import java.util.Hashtable;
import java.util.Scanner;

public class ConsoleApp {
    Hashtable<String,String> data =  new Hashtable<>();
    Scanner sc = new Scanner(System.in);

    public void signIn()
    {
        String input1, input2;
        while (true)
        {
            System.out.print("Enter your username(type exit to exit): ");
            input1 = sc.next();
            if(input1.equals("exit"))
            {
                break;
            }
            System.out.print("Enter your password: ");
            input2 = sc.next();
            data.put(input1, input2);
        }
        System.out.print(data);
    }

    public void logIn()
    {
        System.out.print("Enter your username: ");
        String input = sc.next();
        if(data.containsKey(input))
        {
            System.out.println("Your password was: " + data.get(input));
        }
        else {
            System.out.println("There is no user with such name!");
        }
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.signIn();
        app.logIn();
    }
}
