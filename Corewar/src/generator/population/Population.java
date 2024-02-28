package generator.population;

import java.util.ArrayList;
import java.util.Random;

import generator.crossover.Crossover;
import generator.matcher.Matcher;
import generator.score.Score;

/**
 * This class is used to generate a warrior.
 * 
 */
public class Population {
    //population related attributes
    private Random rand;
    private int genSize;
    private ArrayList<ArrayList<Warrior>> population = new ArrayList<>();

    //breeding related attributes
    private Crossover crossAlgorith;
    private Score scoreAlgorithm;
    private Matcher matchAlgorithm;

    /**
     * This constructor creates a population of warriors.
     * @param generationSize
     * @param seed
     * @param a1
     * @param a2
     * @param a3
     * @requires generationSize positive
     * @requires seed positive
     * @requires a1 != null
     * @requires a2 != null
     * @requires a3 != null
     * @see Random
     * @see Crossover
     * @see Score
     * @see Matcher
     */
    public Population (int generationSize, int seed, Crossover a1, Score a2, Matcher a3) {
        this.genSize = generationSize;
        this.population.add(Generator.createPopulation(genSize, seed));
        this.rand = new Random(seed);

        this.crossAlgorith = a1;
        this.scoreAlgorithm = a2;
        this.matchAlgorithm = a3;

        this.scoreAlgorithm.assess(population.get(0), rand);
    }

    @Override
    /**
     * @return a string representation of the population
     */
    public String toString() {
        return this.toString(this.population.size() - 1);
    }

    /**
     * 
     * @param generation
     * @return  a string representation of the population
     */
    public String toString (int generation) {
        String str = "";
        for (int i=0; i<this.genSize; i++)
            str += "Warrior " + i + "\n" + this.population.get(generation).get(i).toString();
        return str;
    }
    
    /**
     * @return the size of the population
     */
    public int[] getDimentions () {
        int[] res = {genSize, this.population.size()};
        return res;
    }

    /**
     * get a generation
     * @param generation generation index
     * @requires generation positive
     * @return generation if it exists, null if not
     */
    public ArrayList<Warrior> getGeneration (int generation) {
        if (generation > population.size()) return null;
        return this.population.get(generation);
    }

    /**
     * get the last generation
     * @return last generation
     */
    public ArrayList<Warrior> lastGeneration () {
        return this.getGeneration(this.population.size() - 1);
    }

    /**
     * creates a new generation from the last one
     */
    public void breed () {
        ArrayList<Warrior> parentGen = this.lastGeneration();
        Warrior[] partners = this.matchAlgorithm.match(parentGen, rand);
        this.population.add(this.crossAlgorith.crossover(parentGen, partners, rand));
        this.scoreAlgorithm.assess(this.population.get(this.population.size()-1), rand);
    }

    /**
     * prints score statistics about a given generation
     * @param generation
     * @requires generation positive
     */
    public void printScore (int generation) {
        if (generation < this.population.size()) {
            int total = 0;
            int over15000 = 0;
            for (Warrior warrior: this.population.get(generation)) {
                int score = warrior.getScore();
                total += score;
                if (score >= 15000) over15000++;
            }
            System.out.println("Generation " + generation + " has an average score of " + total/this.genSize);
            System.out.println("Survive the whole game: " + over15000);
        }
        else System.out.println("Invalid generation, cannot print stats");
    }

    /**
     * prints score statistics about the last generation
     */
    public void printScore () {
        this.printScore(this.population.size() - 1);
    }
}
