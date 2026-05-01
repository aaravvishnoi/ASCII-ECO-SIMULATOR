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
                        population.get(i).getAllCreatures()
                                .add(new Creature(0, 5, 0, 0, null, "Plant", 100, population.get(i).getSpecies()));

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
                        ArrayList<String> preyList = predator.getSpecies().getPreyList();

                        for (int k = 0; k < preyList.size(); k++) {
                            Population preyPop = findPopulation(preyList.get(k));
                            if (preyPop != null && preyPop.getPopulationCount() > 0) {
                                int rIndex = (int) (Math.random() * preyPop.getPopulationCount());
                                Creature prey = preyPop.getAllCreatures().get(rIndex);

                                double catchChance = (predator.getStrength()
                                        / (predator.getStrength() + prey.getDefence()) + Math.random() * 0.2 - 0.1);
                                
                                if(Math.random() < catchChance){
                                    preyPop.getAllCreatures().remove(prey);
                                    predator.setHealth(100);
                                    break;
                                }

                            }

                        }
                    }
                }

            }

        }
    }

    public void survivalCheck(){
        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < population.get(i).getPopulationCount(); j++) {
                if (population.get(i).getAllCreatures().get(j).getHealth() <= 0) {
                    population.get(i).getAllCreatures().cull();
                    j--;
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
