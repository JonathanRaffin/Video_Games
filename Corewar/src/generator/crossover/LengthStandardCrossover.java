package generator.crossover;

import java.util.ArrayList;
import java.util.Random;

import commands.Command;
import generator.crossover.arguments.BinaryCrossover;
import generator.population.*;

public class LengthStandardCrossover extends Crossover {
    public LengthStandardCrossover (double prob) {
        super(prob);
    }

    /**
     * breeds two warriors by choosing a random length and mapping parents lengths to the new length
     * then randomly chooses one parents instruction for each instruction
     * @param w1 first warrior
     * @param w2 second warrior
     * @param rand random instance
     * @return child warrior
     */
    @Override
    public Warrior crossover(Warrior w1, Warrior w2, Random rand) {
        //Step 1: randomly generate child size (using binary mutation)
        int size = BinaryCrossover.crossover(w1.size(), w2.size(), 6, super.mutationProbability, rand);

        //Step 2: Map warrior lengths to the child's length:
        w1 = w1.clone();
        w2 = w2.clone();
        w1 = this.sizeAdjust(w1, size, rand);
        w2 = this.sizeAdjust(w2, size, rand);

        //Step 3: Pass over code to child selecting each line at random from parent
        ArrayList<Command> child = new ArrayList<Command>();
        for (int i=0; i<size; i++) {
            if (rand.nextDouble() < 0.5) child.add(w1.get(i));
            else child.add(w2.get(i));
        }
        return new Warrior(child);
    }

    private Warrior sizeAdjust (Warrior w, int size, Random rand) {
        //if size is bigger remove random lines
        while (w.size() > size) {
            w.remove(rand.nextInt(w.size()));
        }
        //if size if smaller duplicate random lines
        while (w.size() < size) {
            int index = rand.nextInt(w.size());
            w.add(index, w.get(index));
        }
        return w;
    }
}
