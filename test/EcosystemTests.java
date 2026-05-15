package test;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import ecosystem.Creature;
import ecosystem.Population;
import ecosystem.Species;
import ecosystem.FamilyTree;

public class EcosystemTests {
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
        Creature parentCreature = new Creature(1, 1, 1, 1, null, "null", 0, null);
        Creature childCreature = new Creature(0, 0, 0, 0, parentCreature, null, 0, null);
        FamilyTree tree = new FamilyTree();
        int depth = tree.getDepth(childCreature, 0);
        assertEquals(1, depth);
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
        Creature creature1 = new Creature(0, 0, 0, 0, null, null, 0, null);
        Creature creature2 = new Creature(0, 0, 0, 0, creature1, null, 0, null);
        Creature creature3 = new Creature(0, 0, 0, 0, creature2, null, 0, null);
        FamilyTree tree = new FamilyTree();
        ArrayList<Creature> ancestors = tree.getAncestors(creature3);
        assertEquals(2,ancestors.size());
    }

    @Test
    public void testReproduce(){
        Species testSpecies = new Species("Test", "predator", 10, 1, 50);
        Population testPopulation = new Population(testSpecies);

        Creature deadCreature = new Creature(5, 5, 5, 5, null, "DeadOne", 60, testSpecies);
        testPopulation.getAllCreatures().add(deadCreature);
        testPopulation.reproduce();
        //the creatures reproduce asexually 
        assertEquals(2,testPopulation.getPopulationCount());
    }

    

}