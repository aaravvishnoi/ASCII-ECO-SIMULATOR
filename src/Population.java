import java.util.*;

class Population {

    // Holds arraylist of all creatures of one species
    private ArrayList<Creature> allCreatures;
    private Species species;

    public Population(Species species) {
        this.species = species;
        this.allCreatures = new ArrayList<>();
    }

    public void reproduce() {
        ArrayList<Creature> newborn = new ArrayList<>();
        for (int i = 0; i < allCreatures.size(); i++) {
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
        if (roll < 0.3) {
            int delta = (int) (Math.random() * 3) - 1;
            stat = stat + delta;
            stat = Math.max(1, Math.min(15, stat));
        }
        return stat;
    }

    public void cull(){
        ArrayList<Creature> dead = new ArrayList<>();
        for(int i=0; i < allCreatures.size(); i++){
            if (allCreatures.get(i).getHealth() <= 0 || allCreatures.get(i).getAge() > species.getMaxAge()) {
                dead.add(allCreatures.get(i));
            }
        }
        allCreatures.removeAll(dead);
    }


}