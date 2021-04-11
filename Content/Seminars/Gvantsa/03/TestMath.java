import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestMath {
    @Test
    public void smallNumbers(){
        assertEquals(7, Math.add(3 , 4) );
    }

    @Test
    public void addzero(){
        assertEquals(9, Math.add(9 , 0));
        assertEquals(9, Math.add(0 , 9));
    }

    @Test
    public void absPositiveNumber(){
        assertEquals(5, Math.abs(5));
    }

    @Test
    public void absZero(){
        assertEquals(0, Math.abs(0));
    }

    @Test
    public void absNegativeNumber(){
        assertEquals(19, Math.abs(-19));
    }
}
