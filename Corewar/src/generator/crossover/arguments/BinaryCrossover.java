package generator.crossover.arguments;

import java.util.Random;

public class BinaryCrossover {
    /**
     * Generates a new number from crossing over two numbers bit by bit with possible mutations
     * @param v1 1st number
     * @param v2 2nd number
     * @param size length in bits (note: doesn't have to be int size)
     * @param prob mutation probability
     * @param rand random instance
     * @return new number 
     */
    public static int crossover (int v1, int v2, int size, double prob, Random rand) {
        int res = 0;
        for (int i=0; i<size; i++) {
            double roll = rand.nextDouble();
            if (roll < prob) res += rand.nextInt(2) << i;
            else if ( ( v1 & (1 << i) ) == ( v2 & (1 << i) ) ) res += v1 & (1 << i);
            else res += rand.nextInt(2) << i;
        }
        return Math.max(res, 1);
    }
}
