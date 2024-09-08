package snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import snake.Snake;
//import snake.Apple;

public class SnakeMovementTest {
    @Test 
    @DisplayName("test invalid direction values throws exeption")
    public void testInvalidDirectionValues(){
        Snake snake1 = new Snake();
        assertThrows(IllegalArgumentException.class,  () -> {
            snake1.changeDirection("oppNed");
        }, "Expected IllegalArgumentException");
    }

    @Test
    @DisplayName("Test snake start position")
    public void testSnakeStart(){
        Snake snake2 = new Snake();

        assertEquals("right", snake2.getDirection());

        assertEquals(1, snake2.getSnakeBody().get(0).getxValue(), "Snake in wrong position");
        assertEquals(1, snake2.getSnakeBody().get(0).getyValue(), "Snake in wrong position");
        
        assertEquals(2, snake2.getSnakeBody().get(1).getxValue(), "Snake in wrong position");
        assertEquals(1, snake2.getSnakeBody().get(1).getyValue(), "Snake in wrong position");
        
        assertEquals(3, snake2.getSnakeBody().get(2).getxValue(), "Snake in wrong position");
        assertEquals(1, snake2.getSnakeBody().get(2).getyValue(), "Snake in wrong position");
        
    }

    @Test
    @DisplayName("Test snake move in all directions")
    public void testSnakeMove(){
        Snake snake3 = new Snake();

        snake3.move();

        assertEquals(4, snake3.getSnakeBody().get(2).getxValue(), "Snake in wrong position");
        assertEquals(1, snake3.getSnakeBody().get(2).getyValue(), "Snake in wrong position");

        snake3.changeDirection("down");
        snake3.move();
        assertEquals(4, snake3.getSnakeBody().get(2).getxValue(), "Snake in wrong position");
        assertEquals(2, snake3.getSnakeBody().get(2).getyValue(), "Snake in wrong position");
        
        snake3.changeDirection("left");
        snake3.move();
        assertEquals(3, snake3.getSnakeBody().get(2).getxValue(), "Snake in wrong position");
        assertEquals(2, snake3.getSnakeBody().get(2).getyValue(), "Snake in wrong position");
        
        snake3.changeDirection("up");
        snake3.move();
        assertEquals(3, snake3.getSnakeBody().get(2).getxValue(), "Snake in wrong position");
        assertEquals(1, snake3.getSnakeBody().get(2).getyValue(), "Snake in wrong position");
        
    }

    @Test
    @DisplayName("Test crash in wall")
    public void crashSnake(){
        Snake snake3 = new Snake();

        assertThrows(IllegalArgumentException.class, () -> {
            snake3.move();
            snake3.move();
            snake3.move();
            snake3.move();
            snake3.move();
            snake3.move();
            snake3.move();
            snake3.move();
        },"Expected IllegalArgumentException");
    }

    @Test
    @DisplayName("Test crash in snake it self")
    public void crashSnake2(){
        Snake snake4 = new Snake();

        assertThrows(IllegalArgumentException.class, () -> {
            snake4.changeDirection("left");
            snake4.move();
        }, "Expected IllegalArgumentException");
    }

    //Denne forstår jeg ikke hvorfor ikke går!!! 
    //"snake.XYvalue.getxValue() .... this.coordinate is null" forstår ikke hvorfor. det funker jo i den andre testen!
    @Test
    @DisplayName("Test growth of snake")
    public void snakeGrow(){
        Snake snake4 = new Snake();
        Apple eple = new Apple( 4, 1);
        snake4.grow(eple);
        int snakeLength = snake4.getSnakeBody().size();

        assertEquals(4, snake4.getSnakeBody().size(),"Snake has wrong size");

        //Endret den på linje 108 her "exptected" til 5 istedenfor 4, for hodet vil vel være på 
        //x-koordinat 5 her, siden du la til at den vokser i hodet ahha, enig(?)
        assertEquals(5, snake4.getSnakeBody().get(snakeLength - 1).getxValue(), "Snake in wrong position");
        assertEquals(1, snake4.getSnakeBody().get(snakeLength - 1).getyValue(), "Snake in wrong position");
    }

}
