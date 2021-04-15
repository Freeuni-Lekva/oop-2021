import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathTest {

    @Test
    public void testAdd() {
        assertEquals(4, Math.add(2, 2));
    }

    @Test
    public void testAddNegative() {
        assertEquals(1, Math.add(-1, 2));
    }
}
