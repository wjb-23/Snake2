/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.Rectangle;

/**
 * Creates the walls and barriers on the maps.
 */
public class Wall extends Rectangle{
    Wall(double xpos, double ypos, double width, double height) {
        super(xpos, ypos, width, height);
        this.setStroked(false);
    }

    @Override
    public String toString() {
        return "Wall []";
    }
}
