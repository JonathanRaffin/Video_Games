package generator.matcher;

import java.util.ArrayList;
import java.util.Random;

import generator.population.Warrior;

public class RandomMatcher extends Matcher {
    /**
     * matches warriors randomly
     * @param parents warriors to be paired up
     * @param rand random instance
     * @return pair array (matched by index)
     */
    @Override
    public Warrior[] match(ArrayList<Warrior> parents, Random rand) {
        Warrior[] partners = new Warrior[parents.size()];
        for (int i=0; i<partners.length; i++) {
            partners[i] = parents.get(rand.nextInt(partners.length));
        }
        return partners;
    }
}
