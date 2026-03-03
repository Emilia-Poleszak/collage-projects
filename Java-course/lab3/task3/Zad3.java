/**
 * @author Czarkowska
 * @author Poleszak
 */

import java.util.Scanner;

public class Zad3 {
    public static int N = 3;

    public static void option1()
    {
        for (int i = 1; i <= N; i++)
        {
            int finalI = i;
            Thread t = new Thread(()->{
                Thread.currentThread().setName("Thread " + finalI);
                System.out.println(Thread.currentThread().getName());
            });
            t.start();
        }
    }

    public static void option2(int p)
    {
        if(p < 10)
        {
            System.out.println("Wrong value of parameter p");
            return;
        }
        for (int i = 1; i <= N; i++)
        {
            int finalI = i;
            Thread t = new Thread(()->{
                Thread.currentThread().setName("Thread " + finalI);
                for (int j = 0; j < p; j++)
                    System.out.println(Thread.currentThread().getName());
            });
            t.start();
        }
    }

    public static void option3()
    {
        for (int i = 1; i <= N; i++)
        {
            int finalI = i;
            Thread t = new Thread(()->{
                Thread.currentThread().setName("Thread " + finalI);
                while (true)
                    System.out.println(Thread.currentThread().getName());
            });
            t.start();
        }
    }

    public static void main(String[] args)
    {
        if(N<2) {
            System.out.println("Wrong value of parameter N");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (>=3): ");
        N = sc.nextInt();
        if (N < 3)
        {
            System.out.println("Wrong value of parameter N");
            return;
        }
        System.out.print("Enter number of tested variant (1, 2 or 3): ");
        int option = sc.nextInt();
        if (option == 1)
            option1();
        else if (option == 2)
        {
            System.out.print("Enter p(>=10): ");
            option2(sc.nextInt());
        }
        else if (option == 3)
            option3();
        else
            System.out.println("Wrong input");
    }
}
