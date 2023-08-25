/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

/**
 * Creates an image to serve as the head and tracks its position.
 */
import edu.macalester.graphics.Image;

public class SnakeBody extends Image{
    private double width;
    private double height;
    private double x;
    private double y;


    SnakeBody(double x, double y, double width, double height) {
        super("images/snakebody.png");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setMaxHeight(width -3);
        this.setMaxHeight(height -3);
        this.setCenter(x, y);
    }



    public double getXpos() {
        return this.getPosition().getX();
    }

    public double getYpos() {
        return this.getPosition().getY();
    }

    @Override
    public String toString() {
        return "SnakeBody [dx=" + x + ", dy=" + y + ", width=" + width + "]";
    }
}
