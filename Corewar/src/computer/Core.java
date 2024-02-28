package computer;

import java.util.Random;

import GUI.GUI;
import commands.Command;

public class Core {

    private Memory[] core;
    private int cycle;
    private ProcessTracker players;

    /**
     * Allows to create a core according to a size given in argument and a number of cycles also given in argument.
     * @param size : the number of memory cells you want in the game
     * @param cycle : the number of cycles allowed
     */
    public Core (int size, int cycle) {
        this.core = new Memory[size];
        for (int i=0; i<size; i++) this.core[i] = new Memory();
        this.cycle = cycle;
    }

    public Memory[] getCore () {return this.core;}
    public int getCycle () {return this.cycle;}
    public ProcessTracker getTracker () {return this.players;}

    /**
     * Adds warriors to the core at random locations without collisions.
     * @param rand : initialised instance of Random
     * @param warriors : player instructions
     */
    public void initialise (Random rand, Command[] ...warriors) {
        this.initialise(rand, null, warriors);
    }

    /**
     * Initialize the core.
     * @param rand
     * @param GUI
     * @param warriors
     */
    public void initialise (Random rand, GUI GUI, Command[] ...warriors) {
        if (GUI == null) this.players = new ProcessTracker(warriors.length, this.core.length);
        else this.players = new ProcessTracker(warriors.length, this.core.length, GUI);
        
        int size = this.core.length;
        int[] positions = new int[warriors.length];

        for (int i = 0; i < positions.length; i++)
            positions[i] = Integer.MAX_VALUE;

        for (int i=0; i<warriors.length; i++) {
            //generate randon position
            int pos = rand.nextInt(size);
            for (int j=0; j<positions.length; j++) if (pos >= positions[j]) pos+= warriors[j].length;
            positions[i] = pos;

            //initialise player process and code
            this.players.initialise(i, pos);
            for (int j=0; j<warriors[i].length; j++) this.core[(pos+j)%this.core.length].setCommand(warriors[i][j]);

            //decrement size for new RNG
            size-=warriors[i].length;
        }
    }

    /**
     * Sets the core command to the position given as argument with the command given as argument.
     * @param pos : the position at which the core command is to be changed
     * @param command : the command you want instead of the other one
     */
    public void set (int pos, Command command) {
        this.core[pos].setCommand(command);
    }

    /**
     * Runs a core cycle and returns a status code
     * @returns -1 default, -2 cycles over, n for player winner
     */
    public int execute () {
        int position = this.players.getPosition();
        if (this.cycle == 0) return -2;
        Command command = this.core[position].getCommand();
        command.execute(this.players, this.core);
        
        // Pour stopper la partie lorsqu'il n'y a plus qu'un seul processus actif :
        //if (this.players.isWinner()) return this.players.getActive();

        int res = this.players.setNext();
        this.cycle--;
        return res;
    }  
    
    // To debug and/or to use without graphical interface.
    @Override 
    public String toString () {
        String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[34m"};
        String str = "|";
        for (int i=0; i<this.core.length; i++) {
            if (this.players.hasPosition(i) != -1) str += colors[this.players.hasPosition(i) % colors.length];
            Command command = this.core[i].getCommand();
            str += command.getName() + "\u001B[37m" + "|";
        }
        return str;
    }
}