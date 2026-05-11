class SimulationDriver {
    public static void main(String[] args) throws Exception {
        FileManager fm = new FileManager();
        Config config = fm.readConfig();

        Species Plant = new Species("Plant", "Producer", 4, 0, config.getCarryCapacity());
        Species Worm = new Species("Worm", "Herbivore S", 5, 4, config.getCarryCapacity());
        Species Rabbit = new Species("Rabbit", "Herbivore L", 8, 6, config.getCarryCapacity());
        Species Fox = new Species("Fox", "Predator S", 10, 7, config.getCarryCapacity());
        Species Wolf = new Species("Wolf", "Predator L", 12, 8, config.getCarryCapacity());
        Species Eagle = new Species("Eagle", "Apex", 15, 9, config.getCarryCapacity());

        Worm.addPrey("Plant");
        Rabbit.addPrey("Plant");
        Fox.addPrey("Rabbit");
        Fox.addPrey("Worm");
        Wolf.addPrey("Fox");
        Wolf.addPrey("Rabbit");
        Eagle.addPrey("Wolf");
        Eagle.addPrey("Fox");
        Eagle.addPrey("Rabbit");

        Population plantPop = new Population(Plant, config.getMutationRate());
        Population wormPop = new Population(Worm, config.getMutationRate());
        Population rabbitPop = new Population(Rabbit, config.getMutationRate());
        Population foxPop = new Population(Fox, config.getMutationRate());
        Population wolfPop = new Population(Wolf, config.getMutationRate());
        Population eaglePop = new Population(Eagle, config.getMutationRate());

        for (int i = 0; i < config.getStartingPopulation(); i++) {
            plantPop.getAllCreatures().add(new Creature(0, 0, 0, 0, null, "Plant", 100, Plant));
            wormPop.getAllCreatures().add(new Creature(3, 2, 2, 3, null, "Worm", 100, Worm));
            rabbitPop.getAllCreatures().add(new Creature(6, 4, 4, 5, null, "Rabbit", 100, Rabbit));
            foxPop.getAllCreatures().add(new Creature(7, 6, 4, 6, null, "Fox", 100, Fox));
            wolfPop.getAllCreatures().add(new Creature(6, 8, 6, 7, null, "Wolf", 100, Wolf));
            eaglePop.getAllCreatures().add(new Creature(9, 10, 5, 10, null, "Eagle", 100, Eagle));
        }

        Ecosystem eco = new Ecosystem(config.getPlantGrowthRate());
        eco.addPopulation(plantPop);
        eco.addPopulation(wormPop);
        eco.addPopulation(rabbitPop);
        eco.addPopulation(foxPop);
        eco.addPopulation(wolfPop);
        eco.addPopulation(eaglePop);

        for (int i = 0; i < config.getGenerationCount(); i++) {
            eco.runGeneration(fm, i);
        }
        
    }

}
