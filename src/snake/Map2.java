/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Creates the image, barriers, and obstacles for level two.
 */
public class Map2 implements Map{
    private static final double MAP_BORDER = 60;
    private static final double LINE_LENGTH = 752;

    private Image map;
    Wall topWall, bottomWall, rightWall, leftWall;
    
    public Map2(CanvasWindow canvas) {
        map = new Image(0, 0);
        levelTwo(canvas);
    } 

    private void levelTwo(CanvasWindow canvas) {
        map.setImagePath("images/map2.png");
        map.setMaxWidth(1000);
        topWall = new Wall(0, MAP_BORDER+15, 1000, 5);
        bottomWall = new Wall(0, 1000 - MAP_BORDER-270, 1000, 5);
        rightWall = new Wall(1000-MAP_BORDER-5, 0, 5, 1000);
        leftWall = new Wall(MAP_BORDER, 0, 5, 1000);

        Wall topLine = new Wall(MAP_BORDER, 203, LINE_LENGTH, 31);
        Wall midLine = new Wall(MAP_BORDER+128, 360, LINE_LENGTH, 31);
        Wall bottomLine = new Wall(MAP_BORDER, 516, LINE_LENGTH, 31);
        canvas.add(map);
        canvas.add(topWall);
        canvas.add(bottomWall);
        canvas.add(rightWall);
        canvas.add(leftWall);
        canvas.add(topLine);
        canvas.add(midLine);
        canvas.add(bottomLine);

    }
    
    public double getHeight() {
        return map.getHeight();
    }

    public double getWidth() {
        return map.getWidth();
    }

    @Override
    public String toString() {
        return "Map2 [bottomWall=" + bottomWall + ", leftWall=" + leftWall + ", map=" + map + ", rightWall=" + rightWall
            + ", topWall=" + topWall + "]";
    }
}

