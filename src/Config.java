class Config {
    private int generations;
    private int startingPopulation;
    private double mutationRate;
    private int carryingCapacity;
    private int plantGrowthRate;

    public Config(int generation, int startingPopulation, double mutationRate, int carryingCapacity, int plantGrowthRate){
        this.generations = generation;
        this.startingPopulation = startingPopulation;
        this.mutationRate = mutationRate;
        this.carryingCapacity = carryingCapacity;
        this.plantGrowthRate = plantGrowthRate;

    }

    public int getGenerationCount(){
        return generations;
    }

    public int getStartingPopulation(){
        return startingPopulation;
    }

    public double getMutationRate(){
        return mutationRate;
    }

    public int getCarryCapacity(){
        return carryingCapacity;
    }

    public int getPlantGrowthRate(){
        return plantGrowthRate;
    }
}
