/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Creates the image and barriers for level one.
 */
public class Map1 implements Map{
    private static final double MAP_BORDER = 60;

    private Image map;
    Wall topWall, bottomWall, rightWall, leftWall;
    
    public Map1(CanvasWindow canvas) {
        map = new Image(0, 0);
        levelOne(canvas);
    } 

    private void levelOne(CanvasWindow canvas) {
        map.setImagePath("images/map1.png");
        map.setMaxWidth(1000);
        topWall = new Wall(0, MAP_BORDER, 1000, 5);
        bottomWall = new Wall(0, 1000 - MAP_BORDER-255, 1000, 5);
        rightWall = new Wall(1000-MAP_BORDER-5, 0, 5, 1000);
        leftWall = new Wall(MAP_BORDER, 0, 5, 1000);

        canvas.add(map);
        canvas.add(topWall);
        canvas.add(bottomWall);
        canvas.add(rightWall);
        canvas.add(leftWall);
    }
    
    public double getHeight() {
        return map.getHeight();
    }

    public double getWidth() {
        return map.getWidth();
    }

    @Override
    public String toString() {
        return "Map1 [bottomWall=" + bottomWall + ", leftWall=" + leftWall + ", map=" + map + ", rightWall=" + rightWall
            + ", topWall=" + topWall + "]";
    }
}
