import java.util.Objects;

public class Bird {

    /**
     * All new bird is a male named Bob, is brown, has 0 speed and is not endangered
     */
    private String name = "Bob";
    private String color = "Brown";
    private String sex = "Male";
    private double speed = 0.0;
    private boolean endangered = false;

    /**
     * sets given name of a bird
     * @param name of an object Bird
     */
    public void setName(String name){ this.name = name; }

    /**
     * sets given color of a bird
     * @param color of an object Bird
     */
    public void setColor(String color) { this.color = color; }

    /**
     * sets given sex of a bird
     * @param sex of an object Bird
     */
    public void setSex(String sex){ this.sex = sex; }

    /**
     * sets given speed of a bird
     * @param speed of an object Bird
     */
    public void setSpeed(double speed){ this.speed = speed; }

    /**
     * sets if a bird is endangered or not
     * @param endangered of an object Bird
     */
    public void setEndangered(boolean endangered){ this.endangered = endangered; }

    /**
     * gives speed of a bird
     * @return speed of an object Bird
     */
    public double getSpeed(){ return speed; }

    /**
     * gives name of a bird
     * @return name of an object Bird
     */
    public String getName(){ return name; }

    /**
     * gives color of a bird
     * @return color of an object Bird
     */
    public String getColor() {
        return color;
    }

    /**
     * gives sex of a bird
     * @return sex of an object Bird
     */
    public String getSex(){
        return sex;
    }

    /**
     * checks of a bird is endangered or is not
     * @return endangered of an object Bird
     */
    public boolean isEndangered(){
        return endangered;
    }

    /**
     * gives all data of a bird in a form of String
     * @return String, that contains all data of a Bird
     */
    @Override
    public String toString() {
        return "Bird: " +
                "name: " + name +
                ", color: " + color +
                ", sex: " + sex +
                ", speed: " + speed + " m/s" +
                ", endangered: " + endangered;
    }

    /**
     * prints a birds song
     */
    public void sing(){
        System.out.println(name + ": AAAAAAAAAAAAAAAAA");
    }

    /**
     * sets birds speed to 10.0 m/s and prints a sound of a flying bird
     */
    public void fly(){
        speed = 10.0;
        System.out.println(name + ": WHOOOOOM");
    }

    /**
     * sets birds speed to 0.0 and prints a sound of a landing bird
     */
    public void land(){
        speed = 0.0;
        System.out.println(name + ": BUM");
    }

    /**
     * method equals() checks if given object has equal parameters as a parameter of the method
     * or sets parameters of an objets equal to parameters of object given as a parameter of a method
     * @param o object
     * @return true if objects are equal, false if objects are not equal or object of equal parameters
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Bird theBird)) return false;
        return isEndangered() == theBird.isEndangered() &&
                Double.compare(getSpeed(), theBird.getSpeed()) == 0 &&
                getColor().equals(theBird.getColor()) &&
                getName().equals(theBird.getName()) &&
                getSex().equals(theBird.getSex());
    }

    /**
     * method hashCode() generates hash code of an object
     * @return hash code of an object
     */
    @Override
    public int hashCode(){
        return Objects.hash(getColor(), getName(), getSex(), getSpeed(), isEndangered());
    }

    /**
     * test for class Bird
     * @param args
     */
    public static void main(String[] args)
    {
        Bird bird1 = new Bird(), bird2 = new Bird();
        System.out.println("Is bird1 equal to bird2? \n " + bird1.equals(bird2));
        System.out.println("bird1.hashCode() = " + bird1.hashCode() + "\nbird2.hashCode() = " + bird2.hashCode());
        bird1.fly();
        bird2.sing();
        bird2.setName("Alice");
        bird2.setColor("Black");
        bird2.setSex("Female");
        bird2.setEndangered(true);
        System.out.println("Is bird1 equal to bird2? \n " + bird1.equals(bird2));
        System.out.println(bird1);
        System.out.println(bird2);
        System.out.println("bird1.hashCode() = " + bird1.hashCode() + "\nbird2.hashCode() = " + bird2.hashCode());
        bird1.land();
    }
}