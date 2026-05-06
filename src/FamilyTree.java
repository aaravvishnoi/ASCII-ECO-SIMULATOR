import java.util.ArrayList;
class FamilyTree {
    public int getDepth(Creature creature, int depth) {
        // Implementation for getting the depth of a creature in the family tree
        // base case if((c.getParent() != null, continue))
        // recursive case: call getDepht again but on c.getParent() and increment by 1;
        if (creature.getParent() == null) {
            return depth;
        }
        return getDepth(creature.getParent(), depth+1);
    }


    public String printLineage(Creature creature){
        //should recurisvely print each anecestor name up the chain
        if (creature.getParent() == null) {
            return creature.getName();
        }
        return creature.getName() + " -> " + printLineage(creature.getParent());
    }

    public ArrayList<Creature> getAncestors(Creature creature){
        ArrayList<Creature> ancestors = new ArrayList<Creature>();
        Creature current = creature.getParent();
        while (current != null) {
            ancestors.add(current);
            current = current.getParent();
        }
        return ancestors;
    }
}