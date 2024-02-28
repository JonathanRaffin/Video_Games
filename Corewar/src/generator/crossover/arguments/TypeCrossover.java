package generator.crossover.arguments;

import java.util.Random;

public class TypeCrossover {
    private static final char[] types = {' ', '@', '#', '<'};
    /**
     * Generates a new type from the parent types
     * @param v1 1st type
     * @param v2 2nd type
     * @param prob mutation probability
     * @param rand random instance
     * @return new type 
     */
    public static char crossover (char v1, char v2, int size, double prob, Random rand) {
        double roll = rand.nextDouble();
        if (roll < prob) return TypeCrossover.types[rand.nextInt(TypeCrossover.types.length)];
        else if (roll < 0.5 + prob/2) return v1;
        else return v2;
    }
}
