package generator.population;

import java.util.ArrayList;
import commands.Command;

/**
 * This class is used to generate a warrior.
 * 
 */
public class Warrior {

    /**
     * This array contains all the commands that can be used in a warrior.
     * 
    */
    private ArrayList<Command> code;

    /**
     * This int contains the score of a warrior.
     * 
     */
    private int score;
    
    /**
     * This constructor creates a warrior with a random number of commands.
     * @param code
     * @see Command
     */
    public Warrior (ArrayList<Command> code) {
        this.code = code;
        this.score = 0;
    }

    @Override
    /**
     * This method returns a string representation of a warrior.
     * @return a string representation of a warrior
     * @see Command
    */
    public String toString() {
        String str = "";
        for (Command command: this.code) str += command.toString() + "\n";
        return str;
    }

    /**
     * get the code of a warrior
     * @return warrior code
     */
    public ArrayList<Command> getCode () {
        return this.code;
    }

    /**
     * get the code of a warrior as an array
     * @see Command
     * @see ArrayList
     * @return the code
     */
    public Command[] getArrayCode () {
        Command[] code = this.code.toArray(new Command[this.size()]);
        return code;
    }

    /**
     * gets a warriors score
     * @return score
     */
    public int getScore () {
        return this.score;
    }

    /**
     * set a warriors score
     * @param score
     */
    public void setScore (int score) {
        this.score = score;
    }

    /** 
     * increases a warriors score
     * @param score
    */
    public void increaseScore (int score) {
        this.setScore(this.score + score);
    }

    /**
     * This methode simplifies the standard arraylist.
     * @param i
     * @return the command at index i
     */
    public Command get(int i) {
        return this.code.get(i);
    }

    /** add a command at the index i
     * @param c
     * @see Command
     */
    public void add (int i, Command c) {
        this.code.add(i, c);
    }

    /**
     * add a command to the end of the code
     * @param c
     */
    public void add (Command c) {
        this.code.add(c);
    }

    /**
     * remove a command
     * @param i
     */
    public void remove (int i) {
        this.code.remove(i);
    }

    /**
     * size of the code
     * @return code size
     */
    public int size () {
        return this.code.size();
    }

    @Override
    /**
     * This method returns a clone of a warrior.
     * @see Command
     * @see Warrior
     * @see ArrayList
     * @return a clone of a warrior
     */
    public Warrior clone() {
        Warrior w = new Warrior(new ArrayList<Command>());
        for (Command command: this.code) 
            w.add(new Command(command.getName(), command.getTypeA(), command.getA(), command.getTypeB(), command.getB()));
        return w;
    }
}
