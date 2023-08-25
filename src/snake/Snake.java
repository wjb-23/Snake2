/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public interface Snake {
    public GraphicsGroup getGraphics();
    public int getRatHit();
    public void move(double dx, double dy);
    public boolean wallCollision(CanvasWindow canvas);
    public boolean bodyCollision(CanvasWindow canvas);
    public void testRat(CanvasWindow canvas);
    public double getBodySize();
    public Point getPosition();
    public String getName();
    public boolean outOfBounds();

}
