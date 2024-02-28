package generator.score;

import java.util.ArrayList;
import java.util.Random;

import computer.Core;
import generator.population.Warrior;

/**
 * This class is used to generate a warrior.
 * 
 */
public class SurviveScore extends Score {
    @Override
    /**
     * This method assesses the score of a warrior based on survival other warriors.
     * @param parents
     * @param rand
     * @requires parents != null
     * @requires rand != null
     * @see Random
     * @see Core
     * @see Warrior
     * @see ArrayList
     */
    public void assess(ArrayList<Warrior> parents, Random rand) {
        for (Warrior warrior: parents) {
            Core core = new Core(8000, 15000);
            core.initialise(rand, warrior.getArrayCode());
            int res = -1;
            int i = 0;
            while (res == -1) {
                res = core.execute();
                i++;
            }
            //1 point per cycle survived
            warrior.setScore(i);
        }
    }
}
