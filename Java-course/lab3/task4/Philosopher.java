/**
 * @author Czarkowska
 * @author Poleszak
 */

public class Philosopher implements Runnable {
    private Object leftfork, rightfork;
    private int id;

    public Philosopher(Object leftfork, Object rightfork, int id) {
        if(id == 4) {
            this.leftfork = rightfork;
            this.rightfork = leftfork;
        }
        else {
            this.leftfork = leftfork;
            this.rightfork = rightfork;
        }
        this.id = id;
    }
@Override
    public void run() {
        while (true) {
            try {
                this.think();
                this.eat();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void eat() throws InterruptedException {
        synchronized (leftfork) {
            synchronized (rightfork) {
                System.out.println("Philosopher " + this.id + " is eating");
                Thread.sleep((long) (1000 + Math.random()*5000));
                rightfork.notify();
                leftfork.notify();
            }
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + this.id + " is thinking");
        Thread.sleep((long) (1000 + Math.random()*5000));
    }
}
