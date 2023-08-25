/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Creates the image, barriers, and obstacles for level three.
 */
public class Map3 implements Map{
    private static final double MAP_BORDER = 60;

    private Image map;
    Wall topWall, bottomWall, rightWall, leftWall;
    
    public Map3(CanvasWindow canvas) {
        map = new Image(0, 0);
        levelThree(canvas);
    } 

    private void levelThree(CanvasWindow canvas) {
        map.setImagePath("images/map3.png");
        map.setMaxWidth(1000);

        topWall = new Wall(0, MAP_BORDER, 1000, 5);
        bottomWall = new Wall(0, 1000 - MAP_BORDER-255, 1000, 5);
        rightWall = new Wall(1000-MAP_BORDER-5, 0, 5, 1000);
        leftWall = new Wall(MAP_BORDER, 0, 5, 1000);

        Wall topLeft = new Wall(MAP_BORDER+143, MAP_BORDER, 156, 96);
        Wall topRight = new Wall(this.getWidth()-MAP_BORDER-112, MAP_BORDER, 112, 143);
        Wall middle = new Wall(484, 265, 141, 141);
        Wall middleRight = new Wall(this.getWidth()-MAP_BORDER-190, 359, 190, 32);
        Wall bottomLeft = new Wall(MAP_BORDER, 515, 191, 64);
        Wall bottomRight = new Wall(578, 578, 203, 108);

        canvas.add(map);
        canvas.add(topWall);
        canvas.add(bottomWall);
        canvas.add(rightWall);
        canvas.add(leftWall);
        canvas.add(topLeft);
        canvas.add(topRight);
        canvas.add(middle);
        canvas.add(middleRight);
        canvas.add(bottomLeft);
        canvas.add(bottomRight);
    }
    
    public double getHeight() {
        return map.getHeight();
    }
    
    public double getWidth() {
        return map.getWidth();
    }

    @Override
    public String toString() {
        return "Map3 [bottomWall=" + bottomWall + ", leftWall=" + leftWall + ", map=" + map + ", rightWall=" + rightWall
            + ", topWall=" + topWall + "]";
    }
}

