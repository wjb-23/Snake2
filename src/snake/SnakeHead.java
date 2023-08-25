/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;


import edu.macalester.graphics.Image;

/**
 * Creates an Icon to serve as the head of the snake and tracks its position.
 */
public class SnakeHead extends Image{
    public double dx;
    public double dy;
    private double width;
    private double height;
    private double x;
    private double y;

    SnakeHead(double x, double y, double width, double height) {
        super("images/headright.png");
        this.x = x;
        this.y = y;
        this.setMaxHeight(width - 3);
        this.setMaxHeight(height - 3);
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
        return "SnakeHead [dx=" + dx + ", dy=" + dy + ", height=" + height + ", width=" + width + ", x=" + x + ", y=" + y
            + "]";
    }
}