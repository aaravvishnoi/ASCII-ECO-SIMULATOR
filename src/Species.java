import java.util.*;
/**
 * Represents a species in the ecosystem, including its traits, food relationships,
 * and optional ASCII art for display.
 */
class Species {
    // Stores the species' ASCII art for display in the simulation
    private String ASCII_art;
    private String speciesName;
    private String foodChainRole;
    private int maxAge;
    private int hungerRate;
    private int maxCarryingCapacity;

    private ArrayList<String> preyList;
    private ArrayList<String> predatorList;


    public Species(String speciesName, String foodChainRole, int maxAge, int hungerRate, int maxCarryingCapacity) {
        this.speciesName = speciesName;
        this.foodChainRole = foodChainRole;
        this.maxAge = maxAge;
        this.hungerRate = hungerRate;
        this.maxCarryingCapacity = maxCarryingCapacity;
        this.preyList = new ArrayList<>();
        this.predatorList = new ArrayList<>();
    }

    public String getSpeciesName() {
        return speciesName;

    }

    public String getFoodChainRole() {
        return foodChainRole;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getHungerRate() {
        return hungerRate;
    }

    public void setHungerRate(int newHungerRate) {
        hungerRate = newHungerRate;
    }

    public int getMaxCarry() {
        return maxCarryingCapacity;
    }

    public String getASCII() {
        return ASCII_art;
    }

    public void setAscii(String ASCII) {
        ASCII_art = ASCII;
    }

    public ArrayList<String> getPreyList() {
        return preyList;
    }

    public void addPrey(String preyName) {
        preyList.add(preyName);

    }

    public ArrayList<String> getPredator() {
        return predatorList;
    }

    public void addPredator(String predatorName) {
        predatorList.add(predatorName);
    }

}
