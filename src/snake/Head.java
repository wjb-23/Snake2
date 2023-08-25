/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.Ellipse;

import java.awt.Color;

/**
 * Creates an ellipse to serve as the head of the snake and tracks its position.
 */
public class Head extends Ellipse{
    public double dx;
    public double dy;
    private double width;
    private double height;
    private double x;
    private double y;

    Head(double x, double y, double width, double height, Color color) {
        super(0, 0, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        return "Head [dx=" + dx + ", dy=" + dy + ", height=" + height + ", width=" + width + ", x=" + x + ", y=" + y
            + "]";
    }
}
