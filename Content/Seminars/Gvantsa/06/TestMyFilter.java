import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyFilter {

    private Filter filter;

    @BeforeEach
    public void init(){
        filter = new MyFilter();
    }

    @Test
    public void empty(){
        assertEquals(0, filter.size());
    }

    @Test
    public void addElems(){
        for (int i = 0; i < 10; i++){
            filter.add(i);
            assertEquals(i + 1, filter.size());
        }
    }

    @Test
    public void isPresentTest(){
        for (int i = 0; i < 10; i++){
            assertFalse(filter.isPresent(i));
            filter.add(i);
            assertTrue(filter.isPresent(i));
        }
    }

    @Test
    public void addTwice(){
        filter.add(5);
        assertEquals(1, filter.size());
        filter.add(5);
        assertEquals(1, filter.size());
    }

    @Test
    public void removeTest(){
        for (int i = 0; i < 10; i++){
            filter.add(i);
            assertTrue(filter.isPresent(i));
        }

        for (int i = 0; i < 10; i++){
            filter.remove(i);
            assertFalse(filter.isPresent(i));
        }
    }

    @Test
    public void removeTwice(){
        filter.add(5);
        filter.remove(5);
        assertFalse(filter.isPresent(5));
        filter.remove(5);
    }
}
