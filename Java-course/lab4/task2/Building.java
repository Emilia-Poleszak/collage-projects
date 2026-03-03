/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.LocalTime.now;

public class Building {
    private int time;
    private int floors;
    private double probability;
    private int elevators;
    private int max_passengers;
    public List<Elevator> elevatorList = new ArrayList<>();

    // list of passengers on given floors, used in print method
    // first list contains floors in the building, second contains passengers
    // example: passengers.get(1).set(3, 5);
    // fourth passenger from floor number 1 wants to go to floor number 5
    ArrayList<ArrayList<Integer>> passengersList;

    // queue of waiting passengers, used in Elevator class, is used to save order of waiting passengers
    Queue<Passenger> passengers = new LinkedList<>();

    public Building(int floors, int time, double probability, int elevators, int max_passengers) {
        this.setFloors(floors);
        this.setTime(time);
        this.setProbability(probability);
        this.setElevators(elevators);
        this.setMax_passengers(max_passengers);
        passengersList = new ArrayList<>(floors);
        for (int i = 0; i < floors; i++) passengersList.add(new ArrayList<>());
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getElevators() {
        return elevators;
    }

    public void setElevators(int elevators) {
        this.elevators = elevators;
    }

    public int getMax_passengers() {
        return max_passengers;
    }

    public void setMax_passengers(int max_passengers) {
        this.max_passengers = max_passengers;
    }

    public synchronized void newPassenger() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (this.getProbability() > random.nextDouble()) {
            int start_floor = random.nextInt(floors);
            int end_floor;
            do {
                end_floor = random.nextInt(floors);
            } while (start_floor == end_floor);
            passengersList.get(start_floor).add(end_floor);
            passengers.add(new Passenger(start_floor, end_floor));
        }
    }

    // -1 -> there is no waiting passenger
    public synchronized int whereIsPassengerWaiting() {
        if (passengers.isEmpty()) return -1;
        else return passengers.element().start_floor();
    }

    // -1 -> passenger has already rode other elevator
    public synchronized int getPassengerAtFloor(int floor) {
        if (passengersList.get(floor).isEmpty()) return -1;
        else {
            passengersList.get(floor).removeFirst();
            return Objects.requireNonNull(passengers.poll()).destination_floor();
        }
    }

    public synchronized void print() {
        System.out.println("Building:");
        for (int i = floors - 1; i >= 0; i--) System.out.println("Piętro " + i + ": " + passengersList.get(i));
        for (int i = 1; i <= elevators; i++) {
            System.out.print("Elevator number " + i + ": ");
            elevatorList.get(i - 1).print();
        }
        System.out.println();
    }

    public int totalPassengers() {
        return passengers.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Building building = null;
        Scanner s = new Scanner(System.in);

        // program requires 5 arguments: number of floors in this building, time unit,
        // probability, number of elevators and max number of passengers in this building
        if (args.length != 5) System.out.println("Invalid input arguments");
        else {
            try {
                building = new Building(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input arguments.");
            }
        }
        // It's possible to switch thread type in the beginning
        // 1 means Thread.ofPlatform
        // 2 means Thread.ofVirtual
        int choice;
        while(true){
            System.out.println("Choose thread version\n1 - Platform\n2 - Virtual");
            try {
                String c = s.nextLine();
                choice = Integer.parseInt(c);
                if(0 < choice && choice < 3) break;
                else System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e){
                System.out.println("Invalid choice. Try again.");
            }
        }

        for (int i = 0; i < Objects.requireNonNull(building).getElevators(); i++) {
            building.elevatorList.add(new Elevator(building));
            Thread thread;
            if(choice == 1) thread = Thread.ofPlatform().unstarted(building.elevatorList.get(i));
            else thread = Thread.ofVirtual().unstarted(building.elevatorList.get(i));
            thread.start();
        }

        LocalTime time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (true) {
            time = now();
            switch(time.getHour()){
                case 7, 12, 17: building.setProbability(0.9); //simulation at the early morning, noon, and afternoon
                break;
                case 8, 9, 10, 11, 13, 14, 15, 16: building.setProbability(Double.parseDouble(args[2])); // simulation of normal condition
                break;
                default: building.setProbability(0.0); // simulation at night
                break;
            }
            if (building.totalPassengers() < building.getMax_passengers()) building.newPassenger();
            System.out.println("Time: " + time.format(formatter));
            building.print();
            Thread.sleep(building.getTime() * 1000L);
        }
    }
}
