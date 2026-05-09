import java.util.*;

/**
 * Ecosystem class manages the simulation of an ecosystem containing multiple
 * populations of creatures.
 * It handles the lifecycle of the ecosystem including plant growth, feeding,
 * survival checks,
 * reproduction, and statistical tracking across generations.
 */
class Ecosystem {

    // ArrayList to store all populations in the ecosystem
    private ArrayList<Population> population;

    /**
     * Constructor for Ecosystem class.
     * Initializes an empty ArrayList to store populations.
     */
    public Ecosystem() {
        this.population = new ArrayList<>();
    }

    public void addPopulation(Population p) {
        population.add(p);
    }

    /**
     * Runs a single generation of the ecosystem simulation..
     */
    void runGeneration() {
        plantGrowth(); // Plants grow up to carrying capacity
        feeding(); // Predators hunt and consume prey
        survivalCheck(); // Creatures lose health if unfed; weak creatures are culled
        reproduction(); // Creatures reproduce based on population rules
        avgStats(); // Update average statistics for each population
    }

    /**
     * Handles plant growth during each generation.
     * Plants grow up to their carrying capacity, adding new plant creatures at a
     * fixed growth rate.
     */
    void plantGrowth() {
        // Iterate through all populations to find plant species
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals("Plant")) {
                int currentSize = population.get(i).getPopulationCount();
                int maxCarry = population.get(i).getSpecies().getMaxCarry();

                // Only add plants if population hasn't reached carrying capacity
                if (currentSize < maxCarry) {
                    int growthRate = 5; // Number of new plants to add per generation

                    // Add new plants up to growth rate or carrying capacity, whichever is smaller
                    for (int j = 0; j < growthRate && currentSize + j < maxCarry; j++) {
                        population.get(i).getAllCreatures()
                                .add(new Creature(0, 5, 0, 0, null, "Plant", 100, population.get(i).getSpecies()));
                    }
                }
            }
        }
    }

    /**
     * Simulates feeding behavior for all predator creatures in the ecosystem.
     * Each predator has a chance to find prey based on vision, then a catch chance
     * based on
     * relative strength and defence attributes. Successfully fed predators restore
     * health.
     */
    public void feeding() {
        // Iterate through all populations in the ecosystem
        for (int i = 0; i < population.size(); i++) {
            // Skip plants; only predators hunt
            if (!population.get(i).getSpeciesName().equals("Plant")) {
                // Higher vision means higher chance of finding prey each generation
                for (int j = 0; j < population.get(i).getPopulationCount(); j++) {
                    Creature predator = population.get(i).getAllCreatures().get(j);

                    // Check if predator detects prey (vision/10 is probability of finding prey)
                    if (Math.random() < (predator.getVision() / 10.0)) {
                        ArrayList<String> preyList = predator.getSpecies().getPreyList();

                        // Iterate through all prey types this predator hunts
                        for (int k = 0; k < preyList.size(); k++) {
                            Population preyPop = findPopulation(preyList.get(k));

                            // Only hunt if prey population exists and has creatures
                            if (preyPop != null && preyPop.getPopulationCount() > 0) {
                                // Select random prey from population
                                int rIndex = (int) (Math.random() * preyPop.getPopulationCount());
                                Creature prey = preyPop.getAllCreatures().get(rIndex);

                                // Calculate catch chance based on predator strength vs prey defence
                                // Ratio scaled between 0-1, with small random variance (±0.1)
                                double catchChance = (predator.getStrength()
                                        / (predator.getStrength() + prey.getDefence()) + Math.random() * 0.2 - 0.1);

                                // Attempt to catch prey
                                if (Math.random() < catchChance) {
                                    preyPop.getAllCreatures().remove(prey);
                                    predator.setHealth(100); // Restore health to full
                                    predator.setFed(true); // Mark predator as fed
                                    break; // Exit loop after successful catch
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks the survival of all creatures in the ecosystem.
     * Unfed creatures lose health based on the species hunger rate.
     * Weak creatures (health <= 0) are removed from populations.
     */
    public void survivalCheck() {
        // Iterate through all populations
        for (int i = 0; i < population.size(); i++) {
            // Check each creature in the population
            for (int j = 0; j < population.get(i).getPopulationCount(); j++) {
                Creature c = population.get(i).getAllCreatures().get(j);

                // Unfed creatures lose health proportional to species hunger rate
                if (!c.isFed()) {
                    c.setHealth(c.getHealth() - c.getSpecies().getHungerRate());
                }

                // Reset fed status for next generation
                c.setFed(false);
            }

            // Remove all creatures with health <= 0 from the population
            population.get(i).cull();
        }
    }

    /**
     * Handles reproduction for all populations in the ecosystem.
     * Each population reproduces according to its own reproduction rules and
     * constraints.
     */
    public void reproduction() {
        // Each population handles its own reproduction logic
        for (int i = 0; i < population.size(); i++) {
            population.get(i).reproduce();
        }
    }

    /**
     * Updates average statistics for all populations in the ecosystem.
     * This includes calculating average health, strength, defence, and vision for
     * each population.
     */
    public void avgStats() {
        // Recalculate statistics for each population
        for (int i = 0; i < population.size(); i++) {
            population.get(i).avgStats();
        }
    }

    /**
     * Finds a population in the ecosystem by species name.
     * 
     * @param speciesName the name of the species to search for
     * @return the Population object if found, null if no matching population exists
     */
    public Population findPopulation(String speciesName) {
        // Search through all populations for matching species name
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals(speciesName)) {
                return population.get(i);
            }
        }
        // Return null if no matching population found
        return null;
    }

}
