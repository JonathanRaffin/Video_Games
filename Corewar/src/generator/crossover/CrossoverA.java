package generator.crossover;

import java.util.ArrayList;
import java.util.Random;

import commands.Command;
import generator.population.Warrior;

public class CrossoverA extends Crossover{

    public CrossoverA(double p1) {
        super(p1);
    }

    /**
     * breeds two warriors by crossing over code alternating between w1 and w2
     * @param w1 first warrior
     * @param w2 second warrior
     * @param rand random instance
     * @return child warrior
     */
    @Override
    public Warrior crossover(Warrior w1, Warrior w2, Random rand) {
        ArrayList<Command> c1 = new ArrayList<>();
        boolean bool = true;
        int i = 0;
        while (true) {
            if (bool) {
                if (i>=w1.size()) break;
                c1.add(w1.get(i));
            } else {
                if (i>=w2.size()) break;
                c1.add(w2.get(i));
            }
            bool = !bool;
            i++;
        }
        return new Warrior(c1);
    } 
}
