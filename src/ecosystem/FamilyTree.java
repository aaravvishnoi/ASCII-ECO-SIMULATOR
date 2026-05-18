package ecosystem;

import java.util.ArrayList;

public class FamilyTree {
    public int getDepth(Creature creature, int depth) {
        // Implementation for getting the depth of a creature in the family tree
        // base case if((c.getParent() != null, continue))
        // recursive case: call getDepth again but on c.getParent() and increment by 1;
        if (creature.getParent() == null) {
            return depth;
        }
        return getDepth(creature.getParent(), depth + 1);
    }

    public String printLineage(Creature creature) {
        // should recursively print each ancestor name up the chain
        if (creature.getParent() == null) {
            return creature.getName();
        }
        return creature.getName() + " -> " + printLineage(creature.getParent());
    }
    //recursively adds all the ancestors to a new ArrayList of type Creature 
    public ArrayList<Creature> getAncestors(Creature creature) {
        ArrayList<Creature> ancestors = new ArrayList<Creature>();
        Creature current = creature.getParent();
        //checks if there is no parent, then the ancestor list is empty
        if (current == null) {
            return ancestors;
        } else {
        //Adds the parent and its ancestors to the Array
            ancestors.add(current);
            ancestors.addAll(getAncestors(current));
        }
        return ancestors;
    }
}