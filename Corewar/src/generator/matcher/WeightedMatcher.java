package generator.matcher;

import java.util.ArrayList;
import java.util.Random;

import generator.population.Warrior;

public class WeightedMatcher extends Matcher {
    /**
     * matches warriors randomly with a bias towards stronger warriors
     * @param parents warriors to be paired up
     * @param rand random instance
     * @return pair array (matched by index)
     */
    @Override
    public Warrior[] match(ArrayList<Warrior> parents, Random rand) {
        int[] scores = new int[parents.size()];
        int total = 0;
        for (int i=0; i<parents.size(); i++) {
            int score = parents.get(i).getScore();
            scores[i] = score;
            total += score;
        }
        Warrior[] couples = new Warrior[parents.size()];
        for (int j = 0; j < parents.size(); j++) {
            int index = rand.nextInt(total);
            int tally = 0;
            int i=0;
            while (tally < index) {
                tally += scores[i];
                i++;
            }
            i = Math.min(i, scores.length-1);
            couples[j] = parents.get(i);
        }
        return couples;
    }
}
