package ecosystem;

import java.util.*;

/**
 * Ecosystem class manages the simulation of an ecosystem containing multiple
 * populations of creatures.
 * It handles the lifecycle of the ecosystem including plant growth, feeding,
 * survival checks,
 * reproduction, and statistical tracking across generations.
 */
class Ecosystem {
    private static final String plantName = "Plant";

    // ArrayList to store all populations in the ecosystem
    private ArrayList<Population> population;
    private int plantGrowthRate;

    /**
     * Constructor for Ecosystem class.
     * Initializes an empty ArrayList to store populations.
     */
    public Ecosystem() {
        this.population = new ArrayList<>();
        this.plantGrowthRate = 5;
    }

    public Ecosystem(int plantGrowthRate) {
        this.population = new ArrayList<>();
        this.plantGrowthRate = plantGrowthRate;
    }

    public void addPopulation(Population p) {
        population.add(p);
    }

    /**
     * Runs a single generation of the ecosystem simulation..
     */
    void runGeneration(FileManager fm, int generation) {
        plantGrowth(); // Plants grow up to carrying capacity
        String feedingSummary = feeding(); // Predators hunt and consume prey
        survivalCheck(); // Creatures lose health if unfed; weak creatures are culled
        reproduction(); // Creatures reproduce based on population rules
        String statsSummary = avgStats(); // Update average statistics for each population
        Creature strongestCreature = findStrongestCreature();
        int totalPopulationCount = getTotalPopulationCount();

        StringBuilder log = new StringBuilder();
        log.append("Generation ").append(generation).append("\n");
        log.append("Total population: ").append(totalPopulationCount).append("\n");
        log.append(feedingSummary).append("\n");
        log.append(statsSummary);

        if (strongestCreature != null) {
            FamilyTree familyTree = new FamilyTree();
            String lineage = familyTree.printLineage(strongestCreature);
            log.append("Strongest creature: ")
               .append(strongestCreature.getSpecies().getSpeciesName())
               .append("|strength=").append(strongestCreature.getStrength())
               .append("|defence=").append(strongestCreature.getDefence())
               .append("|speed=").append(strongestCreature.getSpeed())
               .append("|vision=").append(strongestCreature.getVision())
               .append("|health=").append(strongestCreature.getHealth())
               .append("\n")
               .append("Lineage: ").append(lineage).append("\n");
        } else {
            log.append("Strongest creature: none\n");
        }

        fm.writeLog(log.toString());
        System.out.println("Generation " + generation + " complete... Total population: " + totalPopulationCount);
    }

    /**
     * Handles plant growth during each generation.
     * Plants grow up to their carrying capacity, adding new plant creatures at a
     * fixed growth rate.
     */
    void plantGrowth() {
        // Iterate through all populations to find plant species
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals(plantName)) {
                int currentSize = population.get(i).getPopulationCount();
                int maxCarry = population.get(i).getSpecies().getMaxCarry();

                // Only add plants if population hasn't reached carrying capacity
                if (currentSize < maxCarry) {
                    int growthRate = plantGrowthRate; // Number of new plants to add per generation

                    // Add new plants up to growth rate or carrying capacity, whichever is smaller
                    for (int j = 0; j < growthRate && currentSize + j < maxCarry; j++) {
                        population.get(i).getAllCreatures()
                                .add(new Creature(0, 5, 0, 0, null, plantName, 100, population.get(i).getSpecies()));
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
    public String feeding() {
        int catchCount = 0;
        int escapeCount = 0;
        // Iterate through all populations in the ecosystem
        for (int i = 0; i < population.size(); i++) {
            // Skip plants; only predators hunt
            if (!population.get(i).getSpeciesName().equals(plantName)) {
                // Higher vision means higher chance of finding prey each generation
                for (int j = 0; j < population.get(i).getPopulationCount(); j++) {
                    Creature predator = population.get(i).getAllCreatures().get(j);

                    // Check if predator detects prey (vision/10 is probability of finding prey)
                    if (Math.random() < (predator.getVision() / 10.0)) {
                        ArrayList<String> preyList = predator.getSpecies().getPreyList();
                        boolean caughtPrey = false;

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
                                catchChance = Math.max(0.05, Math.min(0.95, catchChance));

                                // Attempt to catch prey
                                if (Math.random() < catchChance) {
                                    preyPop.getAllCreatures().remove(prey);
                                    predator.setHealth(100); // Restore health to full
                                    predator.setFed(true); // Mark predator as fed
                                    catchCount++;
                                    caughtPrey = true;
                                    break; // Exit loop after successful catch
                                }
                            }
                        }

                        if (!caughtPrey) {
                            escapeCount++;
                        }
                    }
                }
            }
        }

        return "Feeding summary|catches=" + catchCount + "|escapes=" + escapeCount;
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

                c.ageIncrement();

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
    public String avgStats() {
        StringBuilder stats = new StringBuilder();

        // Recalculate statistics for each population
        for (int i = 0; i < population.size(); i++) {
            stats.append(population.get(i).avgStats()).append("\n");
        }

        return stats.toString();
    }

    /**
     * Finds the strongest creature across all populations in the ecosystem.
     *
     * @return the creature with the highest combat score, or null if no creatures exist
     */
    public Creature findStrongestCreature() {
        Creature strongestCreature = null;

        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getSpeciesName().equals(plantName)) {
                continue;
            }

            ArrayList<Creature> creatures = population.get(i).getAllCreatures();
            for (int j = 0; j < creatures.size(); j++) {
                Creature currentCreature = creatures.get(j);
                int currentScore = currentCreature.getStrength() + currentCreature.getDefence();
                int strongestScore = strongestCreature == null ? Integer.MIN_VALUE
                        : strongestCreature.getStrength() + strongestCreature.getDefence();

                if (strongestCreature == null || currentScore > strongestScore) {
                    strongestCreature = currentCreature;
                }
            }
        }

        return strongestCreature;
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

    /**
     * Calculates the total number of creatures currently in the ecosystem.
     *
     * @return total creature count across all populations
     */
    private int getTotalPopulationCount() {
        int total = 0;

        for (int i = 0; i < population.size(); i++) {
            total += population.get(i).getPopulationCount();
        }

        return total;
    }

}
