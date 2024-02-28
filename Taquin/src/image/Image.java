package image;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Image {
    BufferedImage picture;

    /**
     * Loads image number 'number' (if its path matches the pattern : images/img + number + .png)
     * @param number : the number of the image to be loaded
     */
    public Image (int number) {
        try {
            this.picture = ImageIO.read(new File("images/img" + number + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads image number 0
     */
    public Image () {
        this(1);
    }

    /**
     * @return the width of the image
     */
    public int getWidth () {
        return this.picture.getWidth();
    }

    /**
     * @return the height of the image
     */
    public int getHeight () {
        return this.picture.getHeight();
    }

    /**
     * @return the image used for the current game
     */
    public BufferedImage getImage () {
        return this.picture;
    }

    /**
     * Cuts the loaded image into several images, depending on the size of the game.
     * @param width : the width of the game
     * @param height : the height of the game
     * @return a list of images
     */
    public BufferedImage[] cutImage (int width, int height) {
        int subImageWidth = this.getWidth() / width;
        int subImageHeight = this.getHeight() / height;
        BufferedImage[] list = new BufferedImage[width * height];
        for (int i = 0; i <  width; i++) 
            for (int j = 0; j < height; j++) 
                list[width * j + i] = this.picture.getSubimage(subImageWidth * i, subImageHeight * j, subImageWidth, subImageHeight);
        return list;
    }
}
