package snake;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class AppleTest {

    @Test
    @DisplayName("Constructor makes valid instance")
    public void testConstructor(){
        Apple test_apple = new Apple();
        int highest = 10;
        int lowest = 0;

        assertTrue(highest >= test_apple.getxValue(), "Error, x-value is too high");
        assertTrue(highest >= test_apple.getyValue(), "Error, y-value is too high");
        assertTrue(lowest <= test_apple.getxValue(),"Error, x-value is too low" );
        assertTrue(lowest <= test_apple.getyValue(),"Error, y-value is too low" );
    }

    @Test
    @DisplayName("Apple cannot be placed in any corners")
    public void testCorners(){
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Apple(0,0);
        }, "Expected IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            new Apple(0,10);
        }, "Expected IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            new Apple(10,0);
        }, "Expected IllegalArgumentException");

        assertThrows(IllegalArgumentException.class, () -> {
            new Apple(10,10);
        }, "Expected IllegalArgumentException");
    }
}
