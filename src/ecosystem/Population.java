package ecosystem;

import java.util.*;

public class Population {

    // Holds arraylist of all creatures of one species
    private ArrayList<Creature> allCreatures;
    private Species species;
    private double mutationRate;

    public Population(Species species) {
        this(species, 0.3);
    }

    public Population(Species species, double mutationRate) {
        this.species = species;
        this.allCreatures = new ArrayList<>();
        this.mutationRate = mutationRate;
    }

    public void reproduce() {
        if (species.getSpeciesName().equals("Plant")) {
            return;
        }

        ArrayList<Creature> newborn = new ArrayList<>();
        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.size() + newborn.size() >= species.getMaxCarry()) {
                break;
            }

            if (allCreatures.get(i).getHealth() >= 60) {
                // creature reproduces
                int newDef = mutate(allCreatures.get(i).getDefence());
                int newStrength = mutate(allCreatures.get(i).getStrength());
                int newSpeed = mutate(allCreatures.get(i).getSpeed());
                int newVision = mutate(allCreatures.get(i).getVision());
                Creature child = new Creature(newSpeed, newDef, newStrength, newVision, allCreatures.get(i),
                        species.getSpeciesName(), 100, species);
                newborn.add(child);
            }
        }
        allCreatures.addAll(newborn);

    }

    public int mutate(int stat) {
        double roll = Math.random();
        if (roll < mutationRate) {
            int delta = (int) (Math.random() * 3) - 1;
            stat = stat + delta;
            stat = Math.max(1, Math.min(15, stat));
        }
        return stat;
    }

    public void cull() {
        ArrayList<Creature> dead = new ArrayList<>();
        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i).getHealth() <= 0 || allCreatures.get(i).getAge() > species.getMaxAge()) {
                dead.add(allCreatures.get(i));
            }
        }
        allCreatures.removeAll(dead);
    }

    public String avgStats() {
        if (allCreatures.size() == 0) {
            return species.getSpeciesName() + "|No creatures";
        }

        double avgDefence = 0;
        double avgStrength = 0;
        double avgVision = 0;
        double avgSpeed = 0;
        for (int i = 0; i < allCreatures.size(); i++) {
            avgDefence += allCreatures.get(i).getDefence();
            avgStrength += allCreatures.get(i).getStrength();
            avgVision += allCreatures.get(i).getVision();
            avgSpeed += allCreatures.get(i).getSpeed();
        }

        avgDefence = avgDefence / allCreatures.size();
        avgSpeed = avgSpeed / allCreatures.size();
        avgStrength = avgStrength / allCreatures.size();
        avgVision = avgVision / allCreatures.size();

        return species.getSpeciesName() + "|" + avgDefence + "|" + avgStrength + "|" + avgVision + "|" + avgSpeed;
    }

    public ArrayList<Creature> getAllCreatures() {
        return allCreatures;
    }

    public String getSpeciesName() {
        return species.getSpeciesName();
    }

    public int getPopulationCount() {
        return allCreatures.size();
    }

    public Species getSpecies() {
        return species;

    }
}
