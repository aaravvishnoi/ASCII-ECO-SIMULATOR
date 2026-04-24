class Creature {
    // Represents one individual creature

    // Basic stats for each creature.
    private int baseSpeed, baseDefence, baseStrength, baseVision, age, hunger, health;
    private String role, name;
    private Creature parent;

    public Creature(int speed, int defence, int strength, int vision, Creature parent, String name, int health) {
        this.age = 0;
        this.name = name;
        this.baseDefence = defence;
        this.baseSpeed = speed;
        this.baseVision = vision;
        this.baseStrength = strength;
        this.parent = parent;
        this.health = health;
        this.hunger = 0;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    public int getStrength(){
        return baseStrength;
    }

    public int getSpeed(){
        return baseSpeed;
    }

    public int getDefence(){
        return baseDefence;
    }

    public int getHunger(){
        return hunger;
    }


    


}
