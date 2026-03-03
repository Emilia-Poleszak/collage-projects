public class Penguin extends Bird{

    /**
     * All new penguins are endangered and are black and white
     */
    private final boolean endangered = true;
    private final String color = "Black and white";

    /**
     * method fly is not applied for class Penguin
     */
    public void fly(){
        System.out.println(getName() + ": Penguins can't fly. :(");
    }

    /**
     * method land also is not applied for class Penguin
     */
    public void land() {
        System.out.println(getName() + ": Penguins can't fly, I was on the ground...");
    }

    /**
     * sets speed of an object to 1 m/s and prints sound of a walking penguin
     */
    public void walk(){
        setSpeed(1);
        System.out.println(getName() + ": TUP TUP");
    }

    /**
     * sets speed of an object to 0 m/s and prints a sound
     */
    public void stop(){
        setSpeed(0);
        System.out.println(getName() + ": BUM");
    }

    /**
     * changes all object data into a String form
     * @return String, that contains all object data
     */
    @Override
    public String toString() {
        return "Penguin: " +
                "name: " + getName() +
                ", color: " + getColor() +
                ", sex: " + getSex() +
                ", speed: " + getSpeed() + " m/s" +
                ", endangered: " + endangered;
    }

    /**
     * test for class Penguin
     */
    public static void main(String[] args){
        Penguin penguin1 = new Penguin(), penguin2 = new Penguin();
        System.out.println("Is bird1 equal to bird2? \n " + penguin1.equals(penguin2));
        System.out.println("penguin1.hashCode() = " + penguin1.hashCode() + "\npenguin2.hashCode() = " + penguin2.hashCode());
        penguin1.fly();
        penguin1.walk();
        penguin2.setName("Linda");
        penguin2.setEndangered(false);
        penguin2.setColor("Grey");
        penguin2.setSex("Female");
        penguin2.sing();;
        penguin1.stop();
        System.out.println("Is bird1 equal to bird2? \n " + penguin1.equals(penguin2));
        System.out.println(penguin1);
        System.out.println(penguin2);
        System.out.println("penguin1.hashCode() = " + penguin1.hashCode() + "\npenguin2.hashCode() = " + penguin2.hashCode());

    }
}