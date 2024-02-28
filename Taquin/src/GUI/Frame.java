package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class Frame extends JFrame {
    private Grid grid;
    protected Container contentPane;
    private int width;
    private int height;
    private boolean images;

    /**
     * Create the game window.
     * @param width : Number of lines in the game.
     * @param height : Number of columns in the game.
     * @param game : Game model.
     * @param images : Boolean that indicates whether to use images in the GUI.
     */
    public Frame (int width, int height, game.Taquin game, boolean images) {
        super("Sliding Puzzle");

        this.width = width;
        this.height = height;

        this.images = images;
        
        this.contentPane = getContentPane();
        this.contentPane.setLayout(new BorderLayout());
        this.contentPane.setBackground(Color.white);
        
        this.initializeSection(game);
        
        super.pack();
        super.setVisible(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @return : the game grid.
     */
    public Grid getGrid () {return this.grid;}
    
    /**
     * Create the margins and the playing area.
     * @param game : Game model.
     */
    public void initializeSection (game.Taquin game) {   
        this.contentPane.setBackground(Color.WHITE);
        
        JPanel areaTop = new JPanel();
        areaTop.setPreferredSize(new Dimension(500,50));
        this.contentPane.add(areaTop, BorderLayout.NORTH);

        JPanel areaLeft = new JPanel();
        areaLeft.setPreferredSize(new Dimension(50,300));
        this.contentPane.add(areaLeft, BorderLayout.WEST);
        
        JPanel areaRight = new JPanel();
        areaRight.setPreferredSize(new Dimension(50,300));
        this.contentPane.add(areaRight, BorderLayout.EAST);

        this.grid = new Grid(this.width, this.height, game, this.images);
        this.contentPane.add(this.grid, BorderLayout.CENTER);

        JPanel areaBottom = new JPanel();
        areaBottom.setPreferredSize(new Dimension(500,50));

        JLabel restart = new JLabel("Cliquez sur la molette ou appuyez sur R pour rejouer !");
        restart.setForeground(Color.black);
        restart.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
        areaBottom.add(restart);

        this.contentPane.add(areaBottom, BorderLayout.SOUTH);
    }
}
