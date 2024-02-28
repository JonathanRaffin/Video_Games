import GUI.*;
import controller.*;

public class Main {
    public static void main (String[] args) {
        int width = args.length > 1 ? Integer.parseInt(args[0]) : 3;
        int height = args.length > 1 ? Integer.parseInt(args[1]) : 3;
        boolean UI = args.length > 2 ? Boolean.parseBoolean(args[2]) : false;
        boolean images = args.length > 3 ? Boolean.parseBoolean(args[3]) : false;

        game.Taquin game = new game.Taquin(width, height);
        game.shuffle(2);

        if (UI) {
            Frame screen = new Frame(width, height, game, images);
            screen.addKeyListener(new KeyControl(game, screen.getGrid(), images));
        } else {
            System.out.println("To play, use the following commands : \n");
            System.out.println("\tu/up to move the piece below the empty square");
            System.out.println("\td/down to move the piece above the empty square");
            System.out.println("\tl/left to move the piece to the left of the empty square");
            System.out.println("\tr/right to move the piece to the right of the empty square");
            System.out.println("\te to leave the game\n");
            System.out.println(game);
            TerminalGame.play(game);
        }
    }
}
