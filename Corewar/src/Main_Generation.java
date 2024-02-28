import generator.crossover.*;
import generator.matcher.*;
import generator.population.*;
import generator.score.*;

public class Main_Generation {
    public static void main(String[] args) {
        Population warriors = new Population(10, 0, new StrongestBiasCrossover(1), new SurviveWinScore(), new WeightedMatcher());
        warriors.printScore();

        int i=0;
        while (true) {
            warriors.breed();
            warriors.printScore();
            i++;
            if (i % 25 == 0) System.out.println(warriors);
        }
    }
}
