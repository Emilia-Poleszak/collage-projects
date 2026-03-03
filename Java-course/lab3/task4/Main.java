/**
 * @author Czarkowska
 * @author Poleszak
 */

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numberOfPhilosophers = 5;
        ArrayList<Thread> philosophers = new ArrayList<Thread>(numberOfPhilosophers);
        ArrayList<Object> forks = new ArrayList<Object>(numberOfPhilosophers);

        for (int i = 0; i < numberOfPhilosophers; i++) forks.add(new Object());

        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosophers.add(new Thread(new Philosopher(forks.get(i), forks.get((i+1) % numberOfPhilosophers), i)));
            philosophers.get(i).start();
        }
    }
}
