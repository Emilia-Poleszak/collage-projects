/**
 * @author Czarkowska
 * @author Poleszak
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Library {
    public static void main (String[] args) {

        try{
            Scanner sc = new Scanner(System.in);

            System.out.println("Choose one of the following options:");
            System.out.println("1. Platform threading");
            System.out.println("2. Virtual threading");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice");
                return;
            }

            System.out.print("Enter W: ");
            int w = sc.nextInt();
            System.out.print("Enter R: ");
            int r = sc.nextInt();
            System.out.print("Enter B: ");
            int b = sc.nextInt();

            BlockingQueue<Integer> books;
            books = new LinkedBlockingQueue<>(b);
            AtomicInteger bookId = new AtomicInteger(0);
            Thread t;
            ArrayList<Thread> threads = new ArrayList<>();

            for (int i = 0; i < w; i++) {
                if (choice == 1) t = Thread.ofPlatform().unstarted(new Writer(bookId, b, books));
                else t = Thread.ofVirtual().unstarted(new Writer(bookId, b, books));
                t.setName("Writer " + i);
                threads.add(t);
            }
            for (int i = 0; i < r; i++) {
                if (choice == 1) t = Thread.ofPlatform().unstarted(new Reader(b, books));
                else t = Thread.ofVirtual().unstarted(new Reader(b, books));
                t.setName("Reader " + i);
                threads.add(t);
            }

            threads.forEach(Thread::start);
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
