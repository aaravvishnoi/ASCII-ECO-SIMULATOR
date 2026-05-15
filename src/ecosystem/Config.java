package ecosystem;

public class Config {
    // data that will be obtained via config file into appropriate variables 
    private int generations;
    private int startingPopulation;
    private double mutationRate;
    private int carryingCapacity;
    private int plantGrowthRate;

    //Constructor for initialising the variables 
    public Config(int generation, int startingPopulation, double mutationRate, int carryingCapacity, int plantGrowthRate){
        this.generations = generation;
        this.startingPopulation = startingPopulation;
        this.mutationRate = mutationRate;
        this.carryingCapacity = carryingCapacity;
        this.plantGrowthRate = plantGrowthRate;

    }
    //Getter for obtaining the the number of generations from the config file
    public int getGenerationCount(){
        return generations;
    }

    //Getter for obtaining the initial number of creatures the simulation begins with
    public int getStartingPopulation(){
        return startingPopulation;
    }

    //probability that a genetic mutation occurs during reproduction
    public double getMutationRate(){
        return mutationRate;
    }

    //maximum population the enviroment can sustain
    public int getCarryCapacity(){
        return carryingCapacity;
    }
    // the rate at which plants will grow
    public int getPlantGrowthRate(){
        return plantGrowthRate;
    }
}
