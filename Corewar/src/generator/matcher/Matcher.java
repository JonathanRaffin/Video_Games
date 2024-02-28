package generator.matcher;

import java.util.ArrayList;
import java.util.Random;

import generator.population.*;

public abstract class Matcher {
    /**
     * return an array matching each warrior to another one in the generation 
     * @param parents warriors to be paired up
     * @param rand random instance
     * @return pair array (matched by index)
     */
    public abstract Warrior[] match (ArrayList<Warrior> parents, Random rand);
}
