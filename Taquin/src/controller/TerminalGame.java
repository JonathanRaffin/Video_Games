package controller;

import java.util.Arrays;
import java.util.Scanner;
import game.Taquin;

public class TerminalGame {
    private static final String[] moves = {"u", "d", "l", "r", "up", "down", "left", "right"};

    /**
     * Allows you to play the game in a terminal.
     * @param game : the current game
     */
    public static void play (Taquin game) {
        Scanner sc = new Scanner(System.in);

        while (!game.isFinished()) {
            String mv = sc.nextLine();
            System.out.println(mv);
            
            if (Arrays.stream(TerminalGame.moves).anyMatch(mv::equals)) {
                boolean[] res = game.move(mv.charAt(0));
                if (res[0]) System.out.print(game);
                else System.out.println("That's not a possible move at the moment !");
            }
            else if (mv.charAt(0) == 'e') {
                System.out.println("You have stopped the game...");
                sc.close();
                break;
            }
            else System.out.println("Invalid move. Must be one of (u/up, d/down, l/left, r/right)");
        }

        sc.close();
        if (game.isFinished()) System.out.println("You won !");
        else System.out.println("You didn't finish the game...");
    }
}
