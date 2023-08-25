/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 */
package snake;

import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.ui.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.DescriptorKey;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Line;

/**
 * Runs the main snake game.
 */
public class SnakeGame {
    
    private CanvasWindow canvas;
    private Map map;
    private List <Snake> snakes = List.of(new DefaultSnake(), new AltSnake());
    private Snake currentSnake = new DefaultSnake();
    private List <Button> snakeButtons = new ArrayList<>();
    private Hawk hawk;
    private Hawk hawk2;
    private GraphicsText gameLost;
    private String direction;
    private List <Rat> rats;
    private GraphicsText ratCount;
    private GraphicsText transition1;
    private GraphicsText transition2;
    private boolean win;
    private GraphicsText winText;
    private Image titleLabel;
    private Button startButton;
    private Image gameOverLabel;
    private GraphicsText snakeSelectLabel;
    private Button quitButton;
    private Button restartButton;
    private Rectangle snakeIndicator;
    private boolean loss = false;
    private Line underline;
    private int ratLimit;
    private Image snakeIcon;
    

    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 750;
    private static final int DELAY = 125;
    private static final int MIN = 200;
    private static final int MAX_Y = 1000 - 60 - 255;
    private static final int MAX_X = 1000 - 60 - 5;

    private SnakeGame() {
        rats = new ArrayList<>();
        canvas = new CanvasWindow("Snake 2.0!", CANVAS_WIDTH, CANVAS_HEIGHT);
        map = new Map1(canvas);
        
        hawk = new Hawk(60, 60, 500, canvas.getWidth(), canvas.getHeight());
        hawk2 = new Hawk(50, 50, 550, canvas.getWidth(), canvas.getHeight());

        gameLost = new GraphicsText();
        gameLost.setCenter(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT * 0.05);
        win = false;
        winText = new GraphicsText("You win!");
        winText.setFontSize(100);
        winText.setPosition(CANVAS_WIDTH/2-winText.getWidth()/2, CANVAS_HEIGHT/2);

        ratCount = new GraphicsText();
        ratCount.setFont(FontStyle.BOLD_ITALIC, 20);
        ratCount.setFillColor(Color.white);
        ratCount.setPosition(80, 40);

        transition1 = new GraphicsText("Welcome to Level 2");
        transition1.setFont(FontStyle.BOLD_ITALIC, 20);
        transition1.setFillColor(Color.white);
        transition1.setPosition(canvas.getWidth() * 0.5, 50);

        transition2 = new GraphicsText("Welcome to Level 3");
        transition2.setFont(FontStyle.BOLD_ITALIC, 20);
        transition2.setFillColor(Color.white);
        transition2.setPosition(canvas.getWidth() * 0.5, 50);

        snakeIndicator = new Rectangle(90, 250, 150, 50);
        snakeIndicator.setStroked(false);
        snakeIndicator.setFilled(false);
        snakeIndicator.setCenter(currentSnake.getGraphics().getCenter());
        canvas.add(snakeIndicator);

        snakeSelectLabel = new GraphicsText("Select Skin");
        snakeSelectLabel.setFont(FontStyle.BOLD_ITALIC, 20);
        snakeSelectLabel.setCenter(300, 290);
        canvas.add(snakeSelectLabel);
        int y = 340;
        for (Snake s : snakes){
            s.getGraphics().setCenter(300, y);
            canvas.add(s.getGraphics());
            y += 40;
        }

        snakeIcon = new Image(0, 0);
        snakeIcon.setImagePath("images/snake.png");
        snakeIcon.setMaxWidth(100);
        snakeIcon.setMaxHeight(100);


        direction = "right"; 
        titleLabel = new Image("images/title.png");
        startButton = new Button("Start");

        gameOverLabel = new Image("images/gameover.png");
        quitButton = new Button("Quit");
        restartButton = new Button("Try Again");

        underline = new Line(snakeSelectLabel.getX(), snakeSelectLabel.getY() + snakeSelectLabel.getHeight() - 7, snakeSelectLabel.getX() + snakeSelectLabel.getWidth(), snakeSelectLabel.getY() + snakeSelectLabel.getHeight() - 7);
        underline.setStrokeColor(Color.blue);
        underline.setStrokeWidth(5);
        canvas.add(underline);

    }


    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.startWindow(game);
    }

    private void addSnakeButton(Snake snake, String title, double y){
        Button button = new Button(title);
        snakeButtons.add(button);
        button.setCenter(500, y);
        canvas.add(button);
        button.onClick(() -> {
            currentSnake = snake;
            snakeIndicator.setCenter(snake.getGraphics().getCenter());
            snakeIndicator.setStrokeColor(Color.blue);
            snakeIndicator.setStrokeWidth(5);
            
        });
    }

    private void startWindow(SnakeGame game) {
        titleLabel.setCenter(CANVAS_WIDTH/2 + 50, 200);
        snakeIcon.setCenter(titleLabel.getX() - 70, titleLabel.getCenter().getY());
        canvas.add(titleLabel);
        canvas.add(snakeIcon);
        startButton.setScale(500);
        startButton.setCenter(500, 300);
        canvas.add(startButton);
        int y = 340;
        for (Snake s : snakes){
            addSnakeButton(s, s.getName(), y);
            y += 40;
        }
        startButton.onClick(() -> startGame(game));
    }

    private void startGame(SnakeGame game) {
        canvas.remove(titleLabel);
        canvas.remove(startButton);
        if (canvas.getElementAt(snakeSelectLabel.getCenter()) != null){
            canvas.remove(snakeSelectLabel);
        }
        for (Snake s : snakes){
            canvas.remove(s.getGraphics());
        }
        for (Button b : snakeButtons){
            canvas.remove(b);
        }

        if (canvas.getElementAt(snakeIndicator.getCenter()) != null){
            canvas.remove(snakeIndicator);
        }
        if (canvas.getElementAt(underline.getCenter()) != null){
            canvas.remove(underline);
        }
        if (canvas.getElementAt(snakeIcon.getCenter()) != null){
            canvas.remove(snakeIcon);
        }
        canvas.draw();
        game.addEverything();
        game.processGame(game);
        game.snakeMovement();
    }

    public void restartGame(SnakeGame game) {
        canvas.removeAll();
        map = new Map1(canvas);
        loss = false;
        addEverything();
        direction = "right";
    }

    private void gameOver(SnakeGame game){
        map = new Map1(canvas);
        gameOverLabel.setCenter(canvas.getWidth()/2 + 50, 200);
        snakeIcon.setImagePath("images/deadsnake.png");
        snakeIcon.setCenter(gameOverLabel.getPosition().getX() - 70, 200);
        canvas.add(snakeIcon);
        canvas.add(gameOverLabel);
        quitButton.setScale(500);
        quitButton.setCenter(500, 400);
        quitButton.onClick(() -> game.canvas.closeWindow());
        restartButton.setScale(500);
        restartButton.setCenter(500, 350);
        canvas.add(restartButton);
        canvas.add(quitButton);
        restartButton.onClick(() -> restartGame(game));
        rats.clear();
        canvas.draw();
    }

    public void addEverything() {
        if (currentSnake.getClass() == DefaultSnake.class){
            currentSnake = new DefaultSnake();
        } else {
            currentSnake = new AltSnake();
        }
        canvas.add(currentSnake.getGraphics());
        rats.clear();
        spawnRats(3, 2);
        canvas.add(hawk, 350, 200);
        canvas.add(hawk2, 700, 400);
        canvas.add(ratCount);
        
        

    }

    public void processGame (SnakeGame game) {
            canvas.animate( () -> {
                if (currentSnake.wallCollision(canvas) == true || currentSnake.bodyCollision(canvas) == true) {
                    canvas.add(gameLost);
                    canvas.pause(1000);
                    loss = true;
                } else if (hawk.snakeCollision(canvas) == true) {
                    canvas.add(gameLost);
                    canvas.pause(1000);
                    loss = true;
                } else if (hawk2.snakeCollision(canvas) == true) {
                    canvas.add(gameLost);
                    canvas.pause(1000);
                    loss = true;

                }
                snakeConstantMovement();
                currentSnake.testRat(canvas);
                hawk.updatePosition(0.03);
                hawk.testRat(canvas);
                hawk.testWall(canvas);   
                hawk2.updatePosition(0.03);
                hawk2.testRat(canvas);
                hawk2.testWall(canvas);    
                respawnRats(5);
                ratCount.setText("Points: " + currentSnake.getRatHit());
                changeMap();

                if (win) {
                    canvas.add(winText);
                    canvas.draw();
                    canvas.pause(5000);
                    canvas.removeAll();
                    canvas.closeWindow();
                }
                if (loss){
                    canvas.removeAll();
                    gameOver(game);

                }
            }
            );
    }

    private void changeMap() {
        if (map.getClass() == Map1.class) {
            ratLimit = 3;
            if (currentSnake.getRatHit() > ratLimit) {
                canvas.removeAll();
                map = new Map3(canvas);
                if (currentSnake.getClass() == DefaultSnake.class){
                    currentSnake = new DefaultSnake();
                } else {
                    currentSnake = new AltSnake();
                }
                addEverything();
                direction = "right";
                canvas.pause(2000);
                canvas.add(transition1);
            }
        } else if (map.getClass() == Map3.class) {
            ratLimit = 4;
            if (currentSnake.getRatHit() > ratLimit) {
                canvas.removeAll();
                map = new Map2(canvas);
                if (currentSnake.getClass() == DefaultSnake.class){
                    currentSnake = new DefaultSnake();
                } else {
                    currentSnake = new AltSnake();
                }
                addEverything();
                direction = "right";
                canvas.pause(2000);
                canvas.add(transition2);
            }
        } else if (map.getClass() == Map2.class) {
            ratLimit = 5;
            if (currentSnake.getRatHit() > ratLimit) {
                win = true;
            }
        }
    }

    
    private void spawnRats(int numRespawns, int numRats){
        for (int i = 0; i < numRats; i++){
            rats.add(new Rat(20, 20, numRespawns)); 
        }
        for (Rat r : rats){
            randomRatSpawn(r);
        }
    }

    private void respawnRats(int numRats){
        for (Rat r : rats){
            if (canvas.getElementAt(r.getPosition()) != null && canvas.getElementAt(r.getPosition()).getClass() != Rat.class){
                if (r.getLives() > 0 ){
                    randomRatSpawn(r);
                }
            }
         }
    }

    private void randomRatSpawn(Rat r){
        int x = new Random().nextInt((800 - 300 + 1) + 300); 
        int y = new Random().nextInt((600 - 200 + 1) + 200);                             
        GraphicsObject g = canvas.getElementAt(x, y);
        if (g.getClass() != Wall.class && g.getClass() != CircleBody.class &&  g.getClass() != Head.class && y > MIN && y < MAX_Y && x > MIN && x < MAX_X){
            canvas.add(r);
            r.setPosition(x, y); // might add an adjustment after a check for walls near the top or bottom of the rats because a portion of their body may spawn in a wall
            canvas.draw();
        }
    }

    

    private void snakeMovement() {
        canvas.onKeyDown(event -> {
            Key key = event.getKey();
            if (key == Key.LEFT_ARROW && direction != "right"){
                currentSnake.move(-currentSnake.getBodySize(), 0);
                direction = "left";
                if (currentSnake.getClass() == DefaultSnake.class){
                    ((DefaultSnake) currentSnake).setImage("images/headleft.png");
                }

                } else if (key == Key.RIGHT_ARROW && direction != "left"){
                    currentSnake.move(currentSnake.getBodySize(), 0);
                    direction = "right";
                    if (currentSnake.getClass() == DefaultSnake.class){
                        ((DefaultSnake)currentSnake).setImage("images/headright.png");
                    }

                } else if (key == Key.UP_ARROW && direction != "down"){
                    currentSnake.move(0, -currentSnake.getBodySize());
                    direction = "up";
                    if (currentSnake.getClass() == DefaultSnake.class){
                        ((DefaultSnake)currentSnake).setImage("images/headup.png");
                    }
    
                } else if (key == Key.DOWN_ARROW && direction != "up"){
                    currentSnake.move(0, currentSnake.getBodySize());
                    direction = "down";
                    if (currentSnake.getClass() == DefaultSnake.class){
                        ((DefaultSnake)currentSnake).setImage("images/headdown.png");
                    }
                }
        });
        
        currentSnake.wallCollision(canvas);
        currentSnake.bodyCollision(canvas);
    }

    public void snakeConstantMovement(){ // combined displacements when key is held leads to a speed boost        
        if (direction != null){
                if (direction == "right"){
                    Timer.doPause(DELAY);
                    currentSnake.move(currentSnake.getBodySize(), 0);
                    direction = "right";
                } 
                if (direction == "left"){
                    Timer.doPause(DELAY);
                    currentSnake.move(-currentSnake.getBodySize(), 0);
                    direction = "left";
                } 
                if (direction == "up"){
                    Timer.doPause(DELAY);
                    currentSnake.move(0, -currentSnake.getBodySize());
                    direction = "up";
                } 
                if (direction == "down"){
                    Timer.doPause(DELAY);
                    currentSnake.move(0, currentSnake.getBodySize());
                    direction = "down";
                
            }
        }
    }

    @Override
    public String toString() {
        return "SnakeGame [canvas=" + canvas + ", currentSnake=" + currentSnake + ", direction=" + direction
            + ", gameLost=" + gameLost + ", gameOverLabel=" + gameOverLabel + ", hawk=" + hawk + ", hawk2=" + hawk2
            + ", loss=" + loss + ", map=" + map + ", quitButton=" + quitButton + ", ratCount=" + ratCount + ", rats="
            + rats + ", restartButton=" + restartButton + ", snakeButtons=" + snakeButtons + ", snakeIndicator="
            + snakeIndicator + ", snakeSelectLabel=" + snakeSelectLabel + ", snakes=" + snakes + ", startButton="
            + startButton + ", titleLabel=" + titleLabel + ", transition1=" + transition1 + ", transition2="
            + transition2 + ", underline=" + underline + ", win=" + win + ", winText=" + winText + "]";
    }
}
