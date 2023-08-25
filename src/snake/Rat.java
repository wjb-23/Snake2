/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.Image;

/**
 * Creates the rat object and keeps track of rat lives.
 */
public class Rat extends Image {

    private double height;
    private int lives;

    Rat (double width, double height, int lives){
        super(0, 0);
        this.height = height;
        this.lives = lives;
        createRat();
    }
    
    /**
     * Assigns image to rat object and sets its maximum height.
     */
    public void createRat(){
        this.setImagePath("images/rat.png");
        this.setMaxHeight(height);
    }

    public int getLives(){
        return lives;
    }

    public void loseLife(){
        lives = lives - 1;
    }

    @Override
    public String toString() {
        return "Rat [height=" + height + ", lives=" + lives + "]";
    }   
}
