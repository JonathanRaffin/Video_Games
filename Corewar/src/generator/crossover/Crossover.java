package generator.crossover;

import java.util.ArrayList;
import java.util.Random;
import generator.population.Warrior;

public abstract class Crossover {
    protected double mutationProbability;

    public Crossover(double p1) {
        this.mutationProbability = p1;
    }

    /**
     * breeds two warriors by crossing over code
     * @param w1 first warrior
     * @param w2 second warrior
     * @param rand random instance
     * @return child warrior
     */
    protected abstract Warrior crossover(Warrior w1, Warrior w2, Random rand);

    /**
     * breeds two warrior lists
     * @param parents 1st couple set
     * @param partners 2nd couple set
     * @param rand random instance
     * @return
     */
    public ArrayList<Warrior> crossover(ArrayList<Warrior> parents, Warrior[] partners, Random rand) {
        ArrayList<Warrior> children = new ArrayList<Warrior>();
        for (int i=0; i<parents.size(); i++) 
            children.add(this.crossover(parents.get(i), partners[i], rand));
        return children;
    }
}
