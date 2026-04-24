import java.util.*;

class Species {
    private String ASCII_art;
    private String speciesName, foodChainRole;
    private int maxAge, hungerRate, maxCarryingCapacity;

    private ArrayList<String> preyList, predatorList;

    public Species(String sName, String fRole, int mAge, int hRate, int carrycap) {
        this.speciesName = sName;
        this.foodChainRole = fRole;
        this.maxAge = mAge;
        this.hungerRate = hRate;
        this.maxCarryingCapacity = carrycap;
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
