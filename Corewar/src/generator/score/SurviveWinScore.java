package generator.score;

import java.util.ArrayList;
import java.util.Random;

import computer.Core;
import generator.population.Warrior;

/**
 * This class is used to generate a warrior.
 * 
 */
public class SurviveWinScore extends SurviveScore {
    @Override
    /**
     * This method assesses the score of a warrior based on survival and beating other warriors.
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
        super.assess(parents, rand);   
        ArrayList<Warrior> survivors = new ArrayList<>();  
        for (Warrior warrior: parents) if (warrior.getScore() > 10000) survivors.add(warrior);
        for (int i=0; i<survivors.size(); i++) 
            for (int j=i+1; j<survivors.size(); j++) {
                Core core = new Core(8000, 15000);
                core.initialise(rand, survivors.get(i).getArrayCode(), survivors.get(j).getArrayCode());
                int res = -1;
                while (res == -1) res = core.execute();
                //5000 points for a win 
                if (res == 0) parents.get(i).increaseScore(5000);
                else if (res == 1) parents.get(j).increaseScore(5000);
                
        }
    }
}
