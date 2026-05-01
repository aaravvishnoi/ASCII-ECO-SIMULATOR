import java.util.*;

class Ecosystem {

    private ArrayList<Population> population;

    public Ecosystem() {
        this.population = new ArrayList<>();

    }

    public void addPopulation(Population p) {
        population.add(p);
    }

    void runGeneration() {

    }

    void plantGrowth() {
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals("Plant")) {
                int currentSize = population.get(i).getPopulationCount();
                int maxCarry = population.get(i).getSpecies().getMaxCarry();
                if (currentSize < maxCarry) {
                    // new plant can be added
                    int growthRate = 5;
                    for (int j = 0; j < growthRate && currentSize + j < maxCarry; j++) {
                        population.get(i).getAllCreatures().add(new Creature(0, 5, 0, 0, null, "Plant", 100, population.get(i).getSpecies()));

                    }

                }
            }
        }

    }

    public void feeding() {
        for (int i = 0; i < population.size(); i++) {
            if (!population.get(i).getSpeciesName().equals("Plant")) {
                // higher vision means higher chance of finding prey each generation
                for (int j = 0; j < population.get(i).getPopulationCount(); j++) {
                    Creature predator = population.get(i).getAllCreatures().get(j);
                    if (Math.random() < (predator.getVision() / 10.0)) {
                    }
                }

            }

        }
    }

    public Population findPopulation(String speciesName) {
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals(speciesName)) {
                return population.get(i);

            }
        }
        return null;

    }
}
