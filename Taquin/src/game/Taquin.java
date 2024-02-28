package game;

import java.util.Arrays;
import java.util.Random;

public class Taquin {
    public int width;
    public int height;
    public int[] emptyCoord;
    public int[][] game;

    /**
     * Makes a game.
     * @param width : columns
     * @param height : rows
     */
    public Taquin (int width, int height) {
        this.width = width;
        this.height = height;
        this.emptyCoord = new int[] {width-1, height-1};
        game = new int[height][width];
        for (int i=0; i<height; i++) for (int j=0; j<width; j++) this.game[i][j] = (i)*width + (j) + 1;
    }

    /**
     * Makes a square game.
     * @param size : number of rows and columns
     */
    public Taquin (int size) {
        this(size, size);
    }

    /**
     * Makes a default 3x3 game.
     */
    public Taquin () {
        this(3, 3);
    }

    @Override 
    public String toString () {
        String display = "";
        int max = width * height;
        for (int i = 0; i < this.game.length; i++) {
            if (i == 0) {
                for (int l = 0; l < this.game[i].length; l++)
                    display += "----";
                display += "-\n";
            }
            display += "| ";
            for (int j = 0; j < this.game[i].length; j++)
                display += this.game[i][j] == max ? "  | " :  this.game[i][j] + " | ";
            display += "\n";
            for (int k = 0; k < this.game[i].length; k++)
                display += "----";
            display += "-\n";
        }
        return display;
    }

    /**
     * Prints the coords of the empty square.
     */
    public void printEmpty () {
        System.out.println(Arrays.toString(this.emptyCoord)); 
    }

    /**
     * Gets the current game.
     * @return 1D game array
     */
    public int[] getGame () {
        int[] game = new int[this.width*this.height];
        for (int i=0; i<this.width; i++) for (int j=0; j<this.height; j++) {
            game[this.width*j + i] = this.game[j][i];
        }
        return game;
    }

    /**
     * Shuffles the game using a Random instance of seed 0.
     */
    public void shuffle () {
        this.shuffle(0);
    }

    /**
     * Shuffles the game using a new Random instance.
     * @param seed : seed for the new Random instance
     */
    public void shuffle (long seed) {
        this.shuffle(new Random(seed));
    }

    /**
     * Shuffles the game using a Random instance.
     * @param rand : Random instance
     */
    public void shuffle (Random rand) {
        int i = this.width*this.height*5;
        char[] moves = {'u', 'd', 'l', 'r'};
        while (i > 0) {
            int index = rand.nextInt(4);
            boolean[] moved = this.move(moves[index]);
            if (moved[0]) i--;
        }
        //avoid already finished shuffle
        if (this.isFinished()) this.shuffle(rand);
    }

    /**
     * Swaps two selected pieces.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void swap (int x1, int y1, int x2, int y2) {
        int tmp = this.game[y1][x1];
        this.game[y1][x1] = this.game[y2][x2];
        this.game[y2][x2] = tmp;
        //move empty coords if necessary 
        if (this.emptyCoord[0] == x1 && this.emptyCoord[1] == y1) {
            this.emptyCoord[0] = x2; this.emptyCoord[1] = y2;
        } else if (this.emptyCoord[0] == x2 && this.emptyCoord[1] == y2) {
            this.emptyCoord[0] = x1; this.emptyCoord[1] = y1;
        }
    }

    /**
     * Moves an adjacent piece into the empty square.
     * @param direction : (must be a char ) l for left piece, r for right piece, u for up piece and d for down piece 
     * @return whether the move was done (index 1) and whether the game is finished (index 2)
     */
    public boolean[] move (char direction) {
        boolean[] res = {true, false};
        int x1 = this.emptyCoord[0];
        int y1 = this.emptyCoord[1];
        try {
            int x2 = x1; int y2 = y1;
            if (direction == 'l') x2--;
            else if (direction == 'r') x2++;
            else if (direction == 'd') y2--;
            else if (direction == 'u') y2++;
            this.swap(x1, y1, x2, y2);
        } catch (Exception e) {res[0] = false;}
        res[1] = this.isFinished();
        return res;
    }

    /**
     * Moves a piece from it's index in the grid.
     * @param index : piece index
     * @return whether the piece was moved
     */
    public boolean[] move (int index) {
        int x = index % this.width;
        int y = (index-x) / this.width;
        boolean[] res = new boolean[2];
        if (x == this.emptyCoord[0]) {
            while (y < this.emptyCoord[1]) res = this.move('d');
            while (y > this.emptyCoord[1]) res = this.move('u');
        }
        else if (y == this.emptyCoord[1]) {
            while (x < this.emptyCoord[0]) res = this.move('l');
            while (x > this.emptyCoord[0]) res = this.move('r');
        }
        return res;
    }

    /**
     * Allows to know if the game is finished.
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished () {
        int[] tmp = this.getGame();
        for (int i=0; i<tmp.length-1; i++) 
            if (tmp[i] > tmp[i+1]) return false;
        return true;
    }
}
