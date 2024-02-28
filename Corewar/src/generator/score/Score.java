package generator.score;

import java.util.ArrayList;
import java.util.Random;

import generator.population.Warrior;

public abstract class Score {
    /**
     * assesses a warriors/populations score
     */
    public abstract void assess (ArrayList<Warrior> parents, Random rand);
}
