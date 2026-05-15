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
                int new_def = mutate(allCreatures.get(i).getDefence());
                int new_strenght = mutate(allCreatures.get(i).getStrength());
                int new_speed = mutate(allCreatures.get(i).getSpeed());
                int new_Vision = mutate(allCreatures.get(i).getVision());
                Creature child = new Creature(new_speed, new_def, new_strenght, new_Vision, allCreatures.get(i),
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

        double avg_def = 0;
        double avg_str = 0;
        double avg_vis = 0;
        double avg_speed = 0;
        for (int i = 0; i < allCreatures.size(); i++) {
            avg_def += allCreatures.get(i).getDefence();
            avg_str += allCreatures.get(i).getStrength();
            avg_vis += allCreatures.get(i).getVision();
            avg_speed += allCreatures.get(i).getSpeed();
        }

        avg_def = avg_def / allCreatures.size();
        avg_speed = avg_speed / allCreatures.size();
        avg_str = avg_str / allCreatures.size();
        avg_vis = avg_vis / allCreatures.size();

        return species.getSpeciesName() + "|" + avg_def + "|" + avg_str + "|" + avg_vis + "|" + avg_speed;
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
