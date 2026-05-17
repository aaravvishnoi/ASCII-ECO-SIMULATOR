package test;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import ecosystem.Creature;
import ecosystem.Population;
import ecosystem.Species;
import ecosystem.FamilyTree;
import ecosystem.Config;
import ecosystem.FileManager;

public class EcosystemTests {

    // =================================================================
    // Population Class Tests
    // =================================================================

    @Test
    public void testMutateUpperBound() {
        // checking that mutation does not exceed upper bound
        Species testSpecies = new Species("TestSpecies", "predator", 20, 5, 100);
        Population testPop = new Population(testSpecies);
        int result = testPop.mutate(15);
        assertTrue(result <= 15);
    }

    @Test
    public void testMutateLowerBound() {
        // checking that mutation does not go below lower bound
        Species testSpecies = new Species("TestSpecies", "predator", 20, 5, 100);
        Population testPop = new Population(testSpecies);
        int result = testPop.mutate(1);
        assertTrue(result >= 1);
    }

    @Test
    public void testMutationAttempt() {
        // checking Mutation rate 0 (no mutation) and 1 (always mutation attempt)
        Species testSpecies = new Species("Test Species", null, 0, 0, 0);
        Population testPopulation = new Population(testSpecies);
        int result = testPopulation.mutate(0);
        int result2 = testPopulation.mutate(1);
        assertTrue(result == 0);
        assertTrue(result2 >= 0);

    }

    @Test
    public void testCull() {
        // create population and add a creature with health 0, verify cull removes it
        Species testSpecies = new Species("Test", "predator", 10, 1, 50);
        Population testPopulation = new Population(testSpecies);

        Creature deadCreature = new Creature(5, 5, 5, 5, null, "DeadOne", 0, testSpecies);
        testPopulation.getAllCreatures().add(deadCreature);

        assertEquals(1, testPopulation.getPopulationCount());
        testPopulation.cull();
        assertEquals(0, testPopulation.getPopulationCount());
    }

    @Test
    public void testReproduce() {
        // Creature with health exactly 60 reproduces
        Species testSpecies = new Species("Test", "predator", 10, 1, 50);
        Population testPopulation = new Population(testSpecies);

        Creature creature1 = new Creature(5, 5, 5, 5, null, "", 60, testSpecies);
        testPopulation.getAllCreatures().add(creature1);
        testPopulation.reproduce();
        // the creatures reproduce asexually
        assertEquals(2, testPopulation.getPopulationCount());

        //Checking if creature with health less then 60 reproduces 
        Species testSpecies2 = new Species("Test2", "prey", 0, 0, 0);
        Population testPopulation2 = new Population(testSpecies2);
        Creature creature2 = new Creature(0, 0, 0, 0, creature1, null, 59, testSpecies2);
        testPopulation2.getAllCreatures().add(creature2);
        testPopulation2.reproduce();
        assertEquals(1, testPopulation2.getPopulationCount());
    }

    @Test
    public void testCarryingCapacity() {
        //checking if reproduction still happens if carryingCapacity has been reached.
        Species testSpecies = new Species("test", "Prey", 0, 0, 1);
        Population testPopulation = new Population(testSpecies);
        Creature creature1 = new Creature(0, 0, 0, 0, null, "Test", 100, testSpecies);
        testPopulation.getAllCreatures().add(creature1);
        testPopulation.reproduce();
        assertEquals(1, testPopulation.getPopulationCount());
    }

    @Test
    public void testEmptyPopulation(){
        //Ensuring an empty population will not reproduce
        Species testSpecies = new Species("Test", null, 0, 0, 0);
        Population testPopulation = new Population(testSpecies);
        testPopulation.reproduce();
        assertEquals(0,testPopulation.getPopulationCount());
    }

    @Test
    public void testPlantReproduction(){
        //Ensuring Plant population does not reproduce
        Species plantSpecies = new Species("Plant", null, 0, 0, 0);
        Population testPopulation = new Population(plantSpecies);
        Creature planCreature = new Creature(0, 0, 0, 0, null, "Plant", 0, plantSpecies);
        testPopulation.getAllCreatures().add(planCreature);
        testPopulation.reproduce();
        assertEquals(1,testPopulation.getPopulationCount());
    }

    // =================================================================
    // FamilyTree Class Tests
    // =================================================================

    @Test
    public void testFamilyTreeDepth() {
        // ensure getDepth returns a non-negative depth for a creature
        Species testSpecies = new Species("TestSpecies", "predator", 20, 5, 100);
        Creature testCreature = new Creature(1, 1, 1, 1, null, "Test", 1, testSpecies);
        FamilyTree tree = new FamilyTree();
        int depth = tree.getDepth(testCreature, 0);
        assertEquals(0, depth);
    }

    @Test
    public void testFamilyDepth() {
        // ensure getDepth returns the correct depth for a creature with a parent
        Creature parentCreature = new Creature(1, 1, 1, 1, null, "null", 0, null);
        Creature childCreature = new Creature(0, 0, 0, 0, parentCreature, null, 0, null);
        FamilyTree tree = new FamilyTree();
        int depth = tree.getDepth(childCreature, 0);
        assertEquals(1, depth);
    }

    @Test
    public void testLineage() {
        // create parent and child and check returned string contains both names with
        // "->" between them
        Creature creatureParent = new Creature(0, 0, 0, 0, null, "Parent", 0, null);
        Creature creatureChild = new Creature(0, 0, 0, 0, creatureParent, "Child", 0, null);
        FamilyTree tree = new FamilyTree();
        String lineage = tree.printLineage(creatureChild);
        assertEquals("Child -> Parent", lineage);
    }

    @Test
    public void testGetAncestors() {
        // ensure getAncestors returns the correct number of ancestors
        Creature creature1 = new Creature(0, 0, 0, 0, null, null, 0, null);
        Creature creature2 = new Creature(0, 0, 0, 0, creature1, null, 0, null);
        Creature creature3 = new Creature(0, 0, 0, 0, creature2, null, 0, null);
        FamilyTree tree = new FamilyTree();
        ArrayList<Creature> ancestors = tree.getAncestors(creature3);
        assertEquals(2, ancestors.size());
    }

    // =================================================================
    // FileManager Class Tests
    // =================================================================

    @Test 
    public void checkConfigFileValue(){
        //ensure all necessary values are present in the config file
        FileManager fileManager = new FileManager();
        try {
            Config config = fileManager.readConfig();
            assertEquals(20, config.getGenerationCount());
            assertEquals(10, config.getStartingPopulation());
            assertEquals(0.3, config.getMutationRate(), 0.001);
            assertEquals(30, config.getCarryCapacity());
            assertEquals(5, config.getPlantGrowthRate());
        } catch (Exception e) {
            fail("Should not have thrown an exception: " + e.getMessage());
        }
    }
}