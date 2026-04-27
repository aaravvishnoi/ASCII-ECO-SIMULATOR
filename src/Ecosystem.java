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
                if(currentSize > maxCarry){
                    // new plant can be added
                    int growthRate =5;
                    for(int j=0; j < growthRate && currentSize + j < maxCarry; j++){
                        population.get(i).getAllCreatures().add(new Creature(0, 5, 0, 0, null, "Plant", 100,population.get(i).getSpecies()));
                        
                    }

            }
        }
    }

}
