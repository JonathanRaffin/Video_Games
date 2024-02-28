package generator.population;

import commands.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to generate a warrior.
 * 
 */
public class Generator {

    /**
     * This array contains all the commands that can be used in a warrior.
     * 
     */
    public static final String[] nomsDeCommands = {"ADD", "CMP","DAT","DJN","JMN","JMP","JMZ","MOV","SLT","SPL","SUB"};

    /**
     * This array contains all the adressages that can be used in a warrior.
     * 
     */
    private static final Character[] adressages = {'#', ' ', '@','<'};
    
    /**
     * This method creates a warrior with a random number of commands.
     * @param rand
     * @return  a warrior
     * @see Command
     * @see Warrior
     * @see Random
     */
    public static Warrior createWarrior (Random rand) {
        int nbCom = rand.nextInt(63) + 1;
        ArrayList<Command> coms = new ArrayList<Command>();

        for (int i=0; i<nbCom;i++) {
            int indexName = rand.nextInt(Generator.nomsDeCommands.length);
            String name = Generator.nomsDeCommands[indexName];
            
            Integer argA = rand.nextInt(8000);
            Integer argB = rand.nextInt(8000);

            int indexAdress = rand.nextInt(Generator.adressages.length);
            Character argTypeA = Generator.adressages[indexAdress];
            indexAdress = rand.nextInt(Generator.adressages.length);
            Character argTypeB = Generator.adressages[indexAdress];

            Command com = new Command(name, argTypeA, argA, argTypeB, argB); 
            coms.add(com);
        }
        return new Warrior(coms);
    }

    /**
     * This method creates a population of warriors.
     * @param size
     * @param seed
     * @see Random
     * @see Warrior
     * @return  an ArrayList of warriors
     */
    public static ArrayList<Warrior> createPopulation (int size, int seed) {
        Random rand = new Random(seed);
        ArrayList<Warrior> population = new ArrayList<>();
        for (int i=0; i<size; i++) population.add(createWarrior(rand));
        return population;
    }
}
