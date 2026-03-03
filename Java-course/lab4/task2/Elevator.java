/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

// Elevator does not stop to take more than 1 passenger

public class Elevator implements Runnable {
    private int passengers = 0;
    private int floor = 0;
    private final Building building;

    private enum Condition {
        RIDING_UP,
        RIDING_DOWN,
        STOP
    }

    private Condition condition = Condition.STOP;

    public Elevator(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            while(true) {
                int where = building.whereIsPassengerWaiting();
                while (where == -1) {
                    Thread.sleep(building.getTime() * 1000L);
                    where = building.whereIsPassengerWaiting();
                }
                this.travelTo(where);
                int destination = building.getPassengerAtFloor(where);
                if (destination == -1) continue;
                passengers++;
                this.travelTo(destination);
                passengers--;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(){
        switch(condition){
            case STOP -> System.out.println("waiting at floor number " + floor + " with " + passengers + " passengers");
            case RIDING_DOWN -> System.out.println("riding down from floor number " + floor + " with " + passengers + " passengers");
            case RIDING_UP -> System.out.println("riding up from floor number " + floor + " with " + passengers + " passengers");
        }
    }

    private void travelTo(int destination) throws InterruptedException {
        while (destination > floor) {
            condition = Condition.RIDING_UP;
            Thread.sleep(building.getTime() * 1000L);
            floor++;
        }
        while (destination < floor) {
            condition = Condition.RIDING_DOWN;
            Thread.sleep(building.getTime() * 1000L);
            floor--;
        }
        condition = Condition.STOP;
    }
}
