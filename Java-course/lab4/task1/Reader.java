/**
 * @author Czarkowska
 * @author Poleszak
 */

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    private BlockingQueue<Integer> queue;
    private ArrayList<Integer> readbooks;
    private int B;

    public Reader(int B, BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.B = B;
        this.readbooks = new ArrayList<>();
    }

    @Override
    public void run() {
        while (readbooks.size() < B) {
            try {
                Integer bookId = this.queue.take();
                if (readbooks.contains(bookId)) {
                    this.queue.put(bookId);
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + ": " + "Starts reading book nr " + bookId);
                readbooks.add(bookId);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                System.out.println(Thread.currentThread().getName() + ": Book nr " + bookId + " read");
                this.queue.put(bookId);
            }
            catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": Reader interrupted");
            }
        }
    }

    public ArrayList<Integer> getReadbooks() {
        return readbooks;
    }

}
