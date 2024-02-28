import java.util.Random;

import computer.*;
import parser.Parser;
import GUI.GUI;

public class Main_CoreWar {
    public static void main(String[] args) {
        int size = 8000;
        int cycles = 15000;
        Random rand = new Random(16);

        GUI GUI = new GUI(size);
        Core core = new Core(size, cycles);
        core.initialise(rand, GUI, new Parser("imp.txt").getCommands(), new Parser("dwarf.txt").getCommands());
        
        int i=0;
        int res;
        while (true) {
            res = core.execute();
            if (res != -1) break;
            try {
                Thread.sleep(GUI.getSpeed());
            } catch (InterruptedException e) {System.out.println("Delay got interrupted");}
            i++;
        };

        System.out.println("Game ended after " + i + " cycles");
        if (res == -2) System.out.println("Game ended due to cycles running out");
        else System.out.println("Game ended. Player " + res + " has won !");
    }
}