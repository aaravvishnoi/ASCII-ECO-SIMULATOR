class SimulationDriver {
    public static void main(String[] args) throws Exception {
        Species Plant = new Species("Plant", "Producer", 4, 0, 30);
        Species Worm = new Species("Worm", "Herbivore S", 5, 8, 20);
        Species Rabbit = new Species("Rabbit", "Herbivore L", 8, 12, 15);
        Species Fox = new Species("Fox", "Predator S", 10, 15, 10);
        Species Wolf = new Species("Wolf", "Predator L", 12, 18, 8);
        Species Eagle = new Species("Eagle", "Apex", 15, 20, 5);

        Worm.addPrey("Plant");
        Rabbit.addPrey("Plant");
        Fox.addPrey("Rabbit");
        Fox.addPrey("Worm");
        Wolf.addPrey("Fox");
        Wolf.addPrey("Rabbit");
        Eagle.addPrey("Wolf");
        Eagle.addPrey("Fox");
        Eagle.addPrey("Rabbit");

        Population plantPop = new Population(Plant);
        Population wormPop = new Population(Worm);
        Population rabbitPop = new Population(Rabbit);
        Population foxPop = new Population(Fox);
        Population wolfPop = new Population(Wolf);
        Population eaglePop = new Population(Eagle);

        for (int i = 0; i < 10; i++) {
            plantPop.getAllCreatures().add(new Creature(0, 0, 5, 0, null, "Plant", 100, Plant));
            wormPop.getAllCreatures().add(new Creature(3, 2, 2, 3, null, "Worm", 100, Worm));
            rabbitPop.getAllCreatures().add(new Creature(6, 4, 4, 5, null, "Rabbit", 100, Rabbit));
            foxPop.getAllCreatures().add(new Creature(7, 6, 4, 6, null, "Fox", 100, Fox));
            wolfPop.getAllCreatures().add(new Creature(6, 8, 6, 7, null, "Wolf", 100, Wolf));
            eaglePop.getAllCreatures().add(new Creature(9, 10, 5, 10, null, "Eagle", 100, Eagle));
        }

        Ecosystem eco = new Ecosystem();
        eco.addPopulation(plantPop);
        eco.addPopulation(wormPop);
        eco.addPopulation(rabbitPop);
        eco.addPopulation(foxPop);
        eco.addPopulation(wolfPop);
        eco.addPopulation(eaglePop);

        FileManager fm = new FileManager();
        Config config = fm.readConfig();

        for(int i=0; i < config.getGenerationCount();i++){
            eco.runGeneration();
        }
        
    }

}