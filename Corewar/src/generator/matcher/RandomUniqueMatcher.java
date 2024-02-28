package generator.matcher;

import java.util.ArrayList;
import java.util.Random;

import generator.population.Warrior;

public class RandomUniqueMatcher extends Matcher {
    /**
     * matches warriors randomly so that each warrior is matched twice
     * @param parents warriors to be paired up
     * @param rand random instance
     * @return pair array (matched by index)
     */
    @Override
    public Warrior[] match(ArrayList<Warrior> parents, Random rand) {
        Warrior[] partners = new Warrior[parents.size()];
        for (int i=0; i<partners.length; i++) partners[i] = parents.get(i);
        for (int i=0; i<partners.length; i++) {
            int index1 = rand.nextInt(partners.length);
            int index2 = rand.nextInt(partners.length);
            Warrior tmp = partners[index1];
            partners[index1] = partners[index2];
            partners[index2] = tmp;
        }
        return partners;
    }
}
