/**
 * @author Czarkowska
 * @author Poleszak
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    private int B;
    private AtomicInteger bookId;
    private BlockingQueue<Integer> queue;

    public Writer(AtomicInteger bookId, int b, BlockingQueue<Integer> queue) {
        this.bookId = bookId;
        this.B = b;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                if (this.bookId.get() < this.B) {
                    System.out.println(Thread.currentThread().getName() + ": Book nr " + this.bookId + " written");
                    queue.put(this.bookId.getAndIncrement());
                }
                else break;
            }
            catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ": Thread interrupted");
            }
        }
    }
}
