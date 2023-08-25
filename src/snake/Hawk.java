/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Creates and moves the hawk objects and allows them to destroy
 * rats and stay within bounds.
 */
public class Hawk extends Image {
    private double width;
    private double height;
    private double angle = Math.random() * Math.PI + Math.PI;
    private double dx;
    private double dy;
    private double maxX;
    private double maxY;
    private double ratHit;

    Hawk (double width, double height, double initialSpeed, double maxX, double maxY){
        super(0, 0);
        this.width = width;
        this.height = height;
        this.maxX = maxX;
        this.maxY = maxY;
        this.dx = initialSpeed * Math.sin(angle);
        this.dy = Math.abs(initialSpeed * Math.cos(angle));
        createHawk();
    }
    
    private void createHawk(){
        this.setImagePath("images/testHawk.png");
        this.setMaxHeight(height);
    }

    public double getXpos() {
        return this.getX();
    }

    public double getYpos() {
        return this.getY();
    }
    
    /**
     * Moves the hawk object across the canvas and accounts for
     * changing directions if out of canvas bounds.
     * @param dt
     */
    public void updatePosition (double dt) {
        double updatedX;
        double updatedY;
        updatedX = this.getX() + dx * dt;
        updatedY = this.getY() + dy * dt;
        if (updatedX < 0 || updatedX > maxX - getWidth()) {
            dx = -dx;
        } else if (updatedY < 0 || updatedY > maxY - getHeight()) {
            dy = -dy;
        } else if ((updatedX == 0 && updatedY == 0) || (updatedX == maxX - getWidth() && updatedY == 0)) {
            dx = -dx;
            dy = -dy;
        }
        this.setPosition(updatedX, updatedY);
    }

    /**
     * Checks if hawk object collides with a wall object and
     * changes directions based on where it collides. 
     * @param canvas
     */
    public void testWall(CanvasWindow canvas){
        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() - 0.01) instanceof Wall) {
                dy = -dy;
            }
        } // top

        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() + getHeight() + 0.01) instanceof Wall) {
                dy = -dy;
            }
        } // bottom

        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() - 0.01, getYpos() + i) instanceof Wall) {
                dx = -dx;
            } 
        } // left

        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + i) instanceof Wall) {
                dx = -dx;
            } // right
        }

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() - 0.01) instanceof Wall) {
            dx = -dx;
            dy = -dy;
        } // top left
        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() - 0.01) instanceof Wall) {
            dx = -dx;
            dy = -dy;
        } // top right
        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + getHeight() + 0.01) instanceof Wall) {
            dx = -dx;
            dy = -dy;
        } // bot left
        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + getHeight() + 0.01) instanceof Wall) {
            dx = -dx;
            dy = -dy;
        } // bot right

    }

    /**
     * Checks if hawk collides with a rat object, and if so removes the rat and increases
     * ratHit by 1.
     * @param canvas
     */
    public void testRat(CanvasWindow canvas){
        if(canvas.getElementAt(getXpos() + getWidth() * 0.5, getYpos() - 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.5, getYpos() - 0.01));
            ratHit += 1;
        } // top
        if(canvas.getElementAt(getXpos() + getWidth() * 0.25, getYpos() - 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.25, getYpos() - 0.01));
            ratHit += 1;
        } // top 2
        if(canvas.getElementAt(getXpos() + getWidth() * 0.75, getYpos() - 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.75, getYpos() - 0.01));
            ratHit += 1;
        } // top 3

        if(canvas.getElementAt(getXpos() + getWidth() * 0.5, getYpos() + getHeight() + 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.5, getYpos() + getHeight() + 0.01));
            ratHit += 1;
        } // bottom
        if(canvas.getElementAt(getXpos() + getWidth() * 0.25, getYpos() + getHeight() + 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.25, getYpos() + getHeight() + 0.01));
            ratHit += 1;
        } // bottom 2
        if(canvas.getElementAt(getXpos() + getWidth() * 0.75, getYpos() + getHeight() + 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() * 0.75, getYpos() + getHeight() + 0.01));
            ratHit += 1;
        } // bottom 3

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.5 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.5 * getHeight()));
            ratHit += 1;
        } // left
        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.25 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.25 * getHeight()));
            ratHit += 1;
        } // left 2
        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.75 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() - 0.01, getYpos() + 0.75 * getHeight()));
            ratHit += 1;
        } // left 3

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.5 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.5 * getHeight()));
            ratHit += 1;
        } // right
        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.25 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.25 * getHeight()));
            ratHit += 1;
        } // right2 
        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.75 * getHeight()) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + 0.75 * getHeight()));
            ratHit += 1;
        } // right3

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() - 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() - 0.01, getYpos() - 0.01));
            ratHit += 1;
        } // top left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() - 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() - 0.01));
            ratHit += 1;
        } // top right

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + getHeight() + 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() - 0.01, getYpos() + getHeight() + 0.01));
            ratHit += 1;
        } // bot left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + getHeight() + 0.01) instanceof Rat) {
            canvas.remove(
                canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + getHeight() + 0.01));
            ratHit += 1;
        } // bot right
    }

    /**
     * Checks if hawk object collides with different parts of the snake
     * and returns a boolean
     * @param canvas
     * @return
     */
    public boolean snakeCollision(CanvasWindow canvas){
        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() - 0.01) instanceof Head) {
                return true;
            } // top
        }

        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() + getHeight() + 0.01) instanceof Head) {
                return true;
            } // bottom
        }
        
        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() - 0.01, getYpos() + i) instanceof Head) {
                return true;
            } // left
        }

        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + i) instanceof Head) {
                return true;
            } // right
        }

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() - 0.01) instanceof Head) {
            return true;
        } // top left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() - 0.01) instanceof Head) {
            return true;
        } // top right

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + getHeight() + 0.01) instanceof Head) {
            return true;
        } // bot left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + getHeight() + 0.01) instanceof Head) {
            return true;
        } // bot right

        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() - 0.01) instanceof CircleBody) {
                return true;
            } // top
        }

        for (int i = 0; i <= getWidth(); i++) {
            if(canvas.getElementAt(getXpos() + i, getYpos() + getHeight() + 0.01) instanceof CircleBody) {
                return true;
            } // bottom
        }
        
        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() - 0.01, getYpos() + i) instanceof CircleBody) {
                return true;
            } // left
        }

        for (int i = 0; i <= getHeight(); i++) {
            if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + i) instanceof CircleBody) {
                return true;
            } // right
        }

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() - 0.01) instanceof CircleBody) {
            return true;
        } // top left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() - 0.01) instanceof CircleBody) {
            return true;
        } // top right

        if(canvas.getElementAt(getXpos() - 0.01, getYpos() + getHeight() + 0.01) instanceof CircleBody) {
            return true;
        } // bot left

        if(canvas.getElementAt(getXpos() + getWidth() + 0.01, getYpos() + getHeight() + 0.01) instanceof CircleBody) {
            return true;
        } // bot right

        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Hawk [angle=" + angle + ", dx=" + dx + ", dy=" + dy + ", height=" + height + ", maxX=" + maxX
            + ", maxY=" + maxY + ", ratHit=" + ratHit + ", width=" + width + "]";
    }
}
