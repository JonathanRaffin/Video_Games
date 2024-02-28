package generator.crossover;

import java.util.ArrayList;
import java.util.Random;

import commands.Command;
import generator.population.*;

public class StandardCrossover extends Crossover {
    public StandardCrossover () {
        super(0);
    }

    /**
     * breeds two warriors by choosing a random length in between both parent lengths
     * then randomly chooses one parents instruction for each instruction
     * @param w1 first warrior
     * @param w2 second warrior
     * @param rand random instance
     * @return child warrior
     */
    @Override
    public Warrior crossover(Warrior w1, Warrior w2, Random rand) {
        int sizeGap = Math.abs( w1.size() - w2.size() );
        int minSize = Math.min( w1.size(), w2.size() );

        //Step 1: randomly generate child size (between w1 and w2 sizes)
        int size = minSize + (int)Math.round(rand.nextDouble()*sizeGap );

        //Step 2: Map warrior lenghts to the child's length:
        Warrior biggest = w1.size() > w2.size() ? w1 : w2; 
        Warrior smallest = w1.size() > w2.size() ? w2 : w1; 
        //randomly remove extra code from biggest warrior and duplicate code from smallest warrior
        while (biggest.size() != size) {
            biggest.remove(rand.nextInt(biggest.size()));
        }
        while (smallest.size() != size) {
            int index = rand.nextInt(smallest.size());
            smallest.add(index, smallest.get(index));
        }

        //Step 3: Pass over code to child selecting each line at random from parent
        ArrayList<Command> child = new ArrayList<Command>();
        for (int i=0; i<size; i++) {
            if (rand.nextDouble() < 0.5) child.add(w1.get(i));
            else child.add(w2.get(i));
        }
        return new Warrior(child);
    }
}
