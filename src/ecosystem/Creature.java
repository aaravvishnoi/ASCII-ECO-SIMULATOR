package ecosystem;

public class Creature {
    // Represents one individual creature

    // Basic stats for each creature.
    private int baseSpeed, baseDefence, baseStrength, baseVision, age, health;
    private String name;
    private Creature parent;
    private Species species;
    private boolean isFed;


    //basic constructor for Creature that uses the values passed in SimulationDriver.java and intialises it
    public Creature(int speed, int defence, int strength, int vision, Creature parent, String name, int health, Species species) {
        this.species = species;
        this.age = 0;
        this.name = name;
        this.baseDefence = defence;
        this.baseSpeed = speed;
        this.baseVision = vision;
        this.baseStrength = strength;
        this.parent = parent;
        this.health = health;
        this.isFed = false;
    }

    // gets the health value of the calling Creature
    public int getHealth() {
        return health;
    }

    //updates the health value of the calling Creature
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    //gets the strength value of the calling Creatures
    public int getStrength() {
        return baseStrength;
    }

    //gets the speed value of the calling Creature
    public int getSpeed() {
        return baseSpeed;
    }
    
    //updates the speed value of the calling creature
    public void setSpeed(int newSpeed){
        this.baseSpeed = newSpeed;
    }

    //gets the defence value of the calling creature
    public int getDefence() {
        return baseDefence;
    }

    //gets the vision value of the calling Creature
    public int getVision() {
        return baseVision;
    }

    //gets the age of the calling Creature
    public int getAge() {
        return age;
    }

    //increments the age of the calling Creature
    public void ageIncrement(){
        this.age += 1;
    }

    //gets the name of the calling Creature
    public String getName(){
        return name;
    }

    //gets the parent of the calling Creature
    public Creature getParent(){
        return parent;
    }

    //gets the Species name of the calling Creature
    public Species getSpecies(){
        return species;
    }
    
    //return a boolean value isFed for the calling Creature
    public boolean isFed(){
        return isFed;
    }

    //updates the boolean setFeed according to the passed parameter
    public void setFed(boolean fed){
        this.isFed = fed;
    }
}
