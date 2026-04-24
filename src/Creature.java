class Creature {
    // Represents one individual creature

    // Basic stats for each creature.
    int baseSpeed, baseDefence, baseStrength, baseVision, age, hunger, health;
    String role, name;
    Creature parent;

    public Creature(int speed, int defence, int strength, int vision, Creature parent, String name, int health) {
        this.age = 0;
        this.name = name;
        this.baseDefence = defence;
        this.baseSpeed = speed;
        this.baseVision = vision;
        this.baseStrength = strength;
        this.parent = parent;
        this.health = health;
    }

}
