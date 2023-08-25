# Snake 2.0
James Bradley, Jason Shi, Sidney Langford

This project is a remake of the classic game Snake. After choosing a snake and starting, the user uses the arrow keys to control the snake and attempt to eat rats. When a rat is eaten, the snake increases in size, the score increases by one, and a new rat randomly spawns. While the user attempts to eat rats, there are also hawks flying around the screen that can eat both rats and the snake. If the user gets enough points, they can move on to the next of three levels, each increasing in difficulty. If the snake collides with the wall of the map, an obstacle, a hawk, or itself then it's game over.

Rather than animating the snake turning like we initally planned in our project proposal, we instead added hawks to the game to add difficulty and make gameplay more interesting rather than making each body segment change when it turns.

Our classes each represent a different aspect of the game. the SnakeGame class is the main class and controls gameplay. When it is run, a start screen appears allowing the user to either assign the playable snake to either a DefaultSnake object (which generates Body and Head objects) or an AltSnake object (which generates NewBody and NewHead objects). When the start button is pressed, the background is generated through the Map1 class which uses Wall objects to establish the barriers.



We referenced https://stackoverflow.com/questions/20959805/adding-a-delay-without-thread-sleep-and-a-while-loop-doing-nothing for the Timer class.
