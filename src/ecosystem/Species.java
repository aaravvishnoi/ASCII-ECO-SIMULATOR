package ecosystem;

import java.util.*;
// Represents a species in the ecosystem, including its traits, food relationships,
// and optional ASCII art for display.
public class Species {
    // Stores the species' ASCII art for display in the simulation
    private String asciiArt;
    private String speciesName;
    private int maxAge;
    private int hungerRate;
    private int maxCarryingCapacity;

    private ArrayList<String> preyList;


    public Species(String speciesName, int maxAge, int hungerRate, int maxCarryingCapacity) {
        this.speciesName = speciesName;
        this.maxAge = maxAge;
        this.hungerRate = hungerRate;
        this.maxCarryingCapacity = maxCarryingCapacity;
        this.preyList = new ArrayList<>();
    }

    //returns Species name
    public String getSpeciesName() {
        return speciesName;

    }

    //gets max age of the Species 
    public int getMaxAge() {
        return maxAge;
    }

    //returns hunger rate of Species
    public int getHungerRate() {
        return hungerRate;
    }

    //updates hunger rate for calling object (Species)
    public void setHungerRate(int newHungerRate) {
        hungerRate = newHungerRate;
    }

    //gets the maximum number of creatures that can exist in the population
    public int getMaxCarry() {
        return maxCarryingCapacity;
    }

    //returns the custom ascii art of the species
    public String getAsciiArt() {
        return asciiArt;
    }

    //updates the ascii art 
    public void setAsciiArt(String ASCII) {
        this.asciiArt = ASCII;
    }

    //returns the preys of the Species 
    public ArrayList<String> getPreyList() {
        return preyList;
    }
    
    //adds preys to the Species prey list
    public void addPrey(String preyName) {
        preyList.add(preyName);

    }

}
