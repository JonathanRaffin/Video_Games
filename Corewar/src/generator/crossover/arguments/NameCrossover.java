package generator.crossover.arguments;

import java.util.Arrays;
import java.util.Random;

import generator.population.Generator;

public class NameCrossover {
    private static final String[][] commandFamilies = {{"JMP", "JMZ", "JMN", "DJN"}, {"ADD", "SUB"}, {"CMP", "SLT"}, {"MOV"}, {"DAT"}};

    /**
     * Generates a new name from the parent name
     * @param v1 1st command
     * @param v2 2nd command
     * @param prob mutation probability
     * @param rand random instance
     * @return new command name 
     */
    public static String crossover (String v1, String v2, double prob, Random rand) {
        double roll = rand.nextDouble();
        if (roll < (3/7)*prob) {
            String[] family = NameCrossover.getFamily(v1);
            return family[rand.nextInt(family.length)];
        }
        else if (roll < (3/7)*prob) {
            String[] family = NameCrossover.getFamily(v2);
            return family[rand.nextInt(family.length)];
        }
        else if (roll < prob) return Generator.nomsDeCommands[rand.nextInt(Generator.nomsDeCommands.length)];
        else if (roll < prob + 0.5*prob) return v1;
        else return v2;
    }

    private static String[] getFamily (String str) {
        int i=0;
        while (i<NameCrossover.commandFamilies.length) 
            if (Arrays.stream(NameCrossover.commandFamilies[i]).anyMatch(str::equals)) 
                return NameCrossover.commandFamilies[i];
        return new String[] {"DAT"};
    }
}
