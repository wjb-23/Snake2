/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Constructs a graphics group and list to create new snake option.
 * Controls movement and collisions.
 */
public class AltSnake implements Snake{
    private GraphicsGroup graphics = new GraphicsGroup();
    private List<GraphicsObject> listGraphics = new ArrayList<>();

    public static final int initialLength = 4;
    public final double bodySize = 15;
    public final double interval = 50;
    private int ratHit = 0;
    private static final int START_X_POS = 80;
    public Image snakeHead = new SnakeHead(0, 0, bodySize, bodySize);
    public Image snakeBody = new SnakeBody(0, 0, bodySize, bodySize);


    public AltSnake() {
        for (int i = initialLength; i >= 0; i--){
            double xPos =  bodySize * i + START_X_POS;
            double yPos = 115;
            GraphicsObject graphic;
            if (i == initialLength) {
                graphic = new Head(xPos, yPos, bodySize, bodySize, Color.GREEN);
            } else if (i == 0) {
                graphic = new CircleBody(xPos, yPos, bodySize, bodySize, Color.BLUE);
                ((CircleBody) graphic).setFilled(false);
                ((CircleBody) graphic).setStroked(false);
            } else {
                graphic = new CircleBody(xPos, yPos, bodySize, bodySize, Color.BLACK);
                ((CircleBody) graphic).setFilled(false);
                ((CircleBody) graphic).setStroked(false);
            }
            snakeBody = new SnakeBody(xPos, yPos, bodySize, bodySize);
            snakeBody.setImagePath("images/rat.png");
            graphics.add(graphic);
            listGraphics.add(graphic);
            if (i < initialLength){
                graphics.add(snakeBody);
                listGraphics.add(snakeBody);
            }
            
        }
        snakeHead.setCenter(listGraphics.get(0).getCenter().getX(), listGraphics.get(0).getCenter().getY());
        snakeHead.setImagePath("images/rat.png");
        graphics.add(snakeHead);
        listGraphics.add(snakeHead);
        ((Head)listGraphics.get(0)).setFilled(false);
        ((Head)listGraphics.get(0)).setStroked(false);
        

    }

    public boolean outOfBounds(){
            if (listGraphics.get(0).getX() < 60 && listGraphics.get(0).getX() > 1000-60 && listGraphics.get(0).getY() < 60 && listGraphics.get(0).getY() > 1000 - 60 -255){
                return true;
            } else {
                return false;
            }
    
        }

    public GraphicsGroup getGraphics() {
        return graphics;
    }

    public int getRatHit() {
        return ratHit;
    }
        
    /**
     * Using the position and movement of the head of the snake causes
     * entire graphics group to move.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy) {
        // get the head of the snake
        double currentX = listGraphics.get(0).getX();
        double currentY = listGraphics.get(0).getY();

        // move the head
        listGraphics.get(0).setPosition(currentX + dx, currentY + dy);
        listGraphics.get(listGraphics.size() - 1).setCenter(listGraphics.get(0).getCenter().getX() + 3, listGraphics.get(0).getCenter().getY());

        for (int i = 1; i < listGraphics.size() - 1; i++){
            double x = listGraphics.get(i).getX(); 
            double y = listGraphics.get(i).getY(); 
            listGraphics.get(i).setPosition(currentX, currentY);
            if (listGraphics.get(i).getClass() == SnakeBody.class){
                listGraphics.get(i).setPosition(listGraphics.get(i-1).getPosition());
            }
            currentX = x;
            currentY = y;
        }
    }

    /**
     * Checks if snake collides with wall and returns a boolean.
     * @param canvas
     */
    public boolean wallCollision(CanvasWindow canvas){
        if(canvas.getElementAt(listGraphics.get(0).getX() + bodySize, listGraphics.get(0).getY() + 0.5 * bodySize) instanceof Wall) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() - 8, listGraphics.get(0).getY() + 0.5 * bodySize) instanceof Wall) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY()) instanceof Wall) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY() + bodySize) instanceof Wall) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Checks if snake collides with itself and returns a boolean.
     * @param canvas
     */
    public boolean bodyCollision(CanvasWindow canvas){
        if(canvas.getElementAt(listGraphics.get(0).getX() + bodySize - 1, listGraphics.get(0).getY() + 0.5 * bodySize) instanceof CircleBody) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() + 1, listGraphics.get(0).getY() + 0.5 * bodySize) instanceof CircleBody) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY() + 1) instanceof CircleBody) {
            return true;
        } if (canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY() + bodySize - 1) instanceof CircleBody) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Checks if snake collides with a rat and if it does, increase ratHit,
     * remove rat, and increase size of snake by one body segment.
     * @param canvas
     */
    public void testRat(CanvasWindow canvas) {
        GraphicsObject eastObj = canvas.getElementAt(listGraphics.get(0).getX() + bodySize + 1, listGraphics.get(0).getY() + 0.5 * bodySize);
        GraphicsObject westObj = canvas.getElementAt(listGraphics.get(0).getX() - 1, listGraphics.get(0).getY() + 0.5 * bodySize);
        GraphicsObject northObj = canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY() - 1);
        GraphicsObject southObj = canvas.getElementAt(listGraphics.get(0).getX() + 0.5 * bodySize, listGraphics.get(0).getY() + bodySize + 1);
        GraphicsObject newGraphic = new CircleBody(0, 0, bodySize, bodySize, Color.RED);
        ((CircleBody) newGraphic).setFilled(false);
        ((CircleBody) newGraphic).setStroked(false);

        SnakeBody newIcon = new SnakeBody(0, 0, bodySize - 1, bodySize - 1);
        newIcon.setImagePath("images/rat.png");


        if (northObj != null && northObj instanceof Rat){
            ((Rat) northObj).loseLife();
            canvas.remove(northObj);
            ratHit += 1;
            listGraphics.add(listGraphics.size() - 1, newGraphic);
            graphics.add(newGraphic);
            newIcon.setCenter(newGraphic.getCenter());
            listGraphics.add(listGraphics.size() - 1, newIcon);
            graphics.add(newIcon);
        } else if(eastObj != null && eastObj instanceof Rat){
            ((Rat) eastObj).loseLife();
            canvas.remove(eastObj);
            ratHit += 1;
            listGraphics.add(listGraphics.size() - 1, newGraphic);
            graphics.add(newGraphic);
            newIcon.setCenter(newGraphic.getCenter());
            listGraphics.add(listGraphics.size() - 1, newIcon);
            graphics.add(newIcon);
        } else if(westObj != null && westObj instanceof Rat){
            ((Rat) westObj).loseLife();
            canvas.remove(westObj);
            ratHit += 1;
            listGraphics.add(listGraphics.size() - 1, newGraphic);
            graphics.add(newGraphic);
            newIcon.setCenter(newGraphic.getCenter());
            listGraphics.add(listGraphics.size() - 1, newIcon);
            graphics.add(newIcon);
        } else if (southObj != null && southObj instanceof Rat){
            ((Rat) southObj).loseLife();
            canvas.remove(southObj);
            ratHit += 1;
            listGraphics.add(listGraphics.size() - 1, newGraphic);
            graphics.add(newGraphic);
            newIcon.setCenter(newGraphic.getCenter());
            listGraphics.add(listGraphics.size() - 1, newIcon);
            graphics.add(newIcon);
        }
    }
    public double getBodySize(){
        return bodySize;
    }

    public Point getPosition(){
        return this.getPosition();
    }

    public String getName(){
        return "Baby Rats";
    }

    @Override
    public String toString() {
        return "AltSnake [bodySize=" + bodySize + ", graphics=" + graphics + ", interval=" + interval
            + ", listGraphics=" + listGraphics + ", ratHit=" + ratHit + "]";
    }

}