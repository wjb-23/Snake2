/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.Ellipse;
import java.awt.Color;

/**
 * Creates the inner segments of the snake and sets color and position.
 */
public class CircleBody extends Ellipse{

    CircleBody(double x, double y, double width, double height, Color color) {
        super(0, 0, width, height);
        this.setCenter(x, y);
        this.setFillColor(color);

    }

    @Override
    public String toString() {
        return "CircleBody []";
    }

}
