package snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import snake.XYvalue;

public class XYvalueTest {

    @Test
    @DisplayName("Constructor 1 makes valid instance")
    public void testConstructor1(){
        XYvalue test1 = new XYvalue();
        int highest = 10;
        int lowest = 0;

        assertTrue(highest >= test1.getxValue(), "Error, x-value is too high");
        assertTrue(highest >= test1.getyValue(), "Error, y-value is too high");
        assertTrue(lowest <= test1.getxValue(),"Error, x-value is too low" );
        assertTrue(lowest <= test1.getyValue(),"Error, y-value is too low" );
    }

    @Test
    @DisplayName("Constructor 2 makes valid instance")
    public void testConstructor2(){
        XYvalue test2 = new XYvalue(0, 10);
        assertEquals(0, test2.getxValue());
        assertEquals(10, test2.getyValue());
        

    }
    
    @Test
    @DisplayName("Invalid values throw exeption")
    public void testInvalidValues(){
       assertThrows(IllegalArgumentException.class, () -> {
            new XYvalue(-1, 2);
        }, "Expected IllegalArgumentException");
            
    }

    
}
