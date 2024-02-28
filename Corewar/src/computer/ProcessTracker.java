package computer;

import GUI.GUI;
import operations.Operations;
import java.awt.Color;
import java.util.ArrayList;

public class ProcessTracker {
    /**
     * array of [activeProcess, nbProcesses]
     */
    private int[][] processes;
    private ArrayList<ArrayList<Integer>> positions;
    private int size;
    private int active;
    private GUI GUI;

    public ProcessTracker(int nbPlayer, int size) {
        this.processes = new int[nbPlayer][2];
        this.positions = new ArrayList<>();
        for (int i=0; i<nbPlayer; i++) {
            this.positions.add(new ArrayList<Integer>());
            this.processes[i][0] = 0;
            this.processes[i][1] = 1;
        }
        this.size = size;
        this.active = 0;
    }

    public ProcessTracker(int nbPlayer, int size, GUI GUI) {
        this(nbPlayer, size);
        this.GUI = GUI;
    }

    public int getActive () {return this.active;}

    /**
     * Gets the current player's position in the core.
     * @return position
     */
    public int getPosition () {
        int process = this.processes[this.active][0];
        return this.positions.get(this.active).get(process);
    }

    /**
     * Initialises a player position in the core.
     * @param player : player index
     * @param position : absolute position
     */
    public void initialise (int player, int position) {
        this.positions.get(player).add(position);
    }

    /**
     * Checks if a player is at specified position.
     * @param pos : position to check
     * @return index of player, -1 if no player is at that position
     */
    public int hasPosition (int pos) {
        int i=0; int j=0;
        while (i<this.positions.size()) {
            while (j<this.positions.get(i).size()) {
                if (this.positions.get(i).get(j) == pos) return i;
                j++;
            }
            i++;
        }
        return -1;
    }

    /**
     * Turns a square black on the GUI if it exists.
     * @param pos : the square to change color
     */
    public void DATPlaced (int pos) {
        if (this.GUI != null) {
            this.GUI.setPanelColor(pos, Color.black);
        }
    }

    /**
     * Increments active players position.
     */
    public void incrementPosition () {
        this.updatePosition(this.active, 1);
    }

    /**
     * Updates active players position.
     * @param increment : new relative position
     */
    public void updatePosition (int increment) {
        this.updatePosition(this.active, increment);
    }

    /**
     * Updates a players position.
     * @param player : player index
     * @param increment : new relative position
     */
    public void updatePosition(int player, int increment) {
        int process = this.processes[player][0];
        this.positions.get(player).set(process, Operations.getPos(this.positions.get(player).get(process), increment, this.size));
        if (this.GUI != null) {
            this.GUI.setPanelColor(this.positions.get(player).get(process), player);
        }
    }

    /**
     * Kills a players active process.
     * @param player : player index
     */
    public void kill (int player) {
        this.processes[player][1]--;
        this.positions.get(player).remove(this.processes[player][0]);
        if (this.processes[player][1] == -1) this.processes[player][0] = -1;
        else if (this.processes[player][1] > this.processes[player][0]) this.processes[player][0] = 0;
        else this.processes[player][0]--;
    }

    /**
     * Kills active player process.
     */
    public void kill () {
        this.kill(this.active);
    }

    /**
     * Sets active process to next player if possible.
     * @return -1 if there is a next player, winner index if only one remains
     */
    public int setNext () {
        if (this.processes.length == 1) {
            if (this.processes[0][0] != -1) return -1;
            else return 0;
        }       
        int tried = 0;
        while (tried < this.processes.length-1) {
            this.active = (this.active+1) % this.processes.length;
            if (this.processes[active][0] != -1) {
                this.processes[active][0] = (this.processes[active][0] + 1) % this.processes[active][1];
                return -1;
            }
            tried++;
        }
        return (this.active+1) % this.processes.length;
    }

    /**
     * Checks if actual player process is the last active process.
     * @return true if actual player process is the last active process, false without
     */
    public boolean isWinner() {
        if (this.setNext() == this.setNext()) return true;
        return false;
    }
}