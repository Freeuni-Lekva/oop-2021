import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class StackTest {
    @Test
    public void pushObject() {
        Stack s = new Stack();
        s.push(null);
    }

    @Test
    public void popObject() {
        Stack s = new Stack();
        s.push(5);
        Integer elem = (Integer) s.pop();
        assertEquals(Integer.valueOf(5), elem);
    }

    @Test
    public void growStack() {
        Stack s = new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push("fooo");
        assertEquals(new String("fooo"), s.pop());
        assertEquals(5, s.pop());
        assertEquals(4, s.pop());
        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
    }

    @Test
    public void popOnEmpty() {
        Stack s = new Stack();
        assertThrows(PopOnEmptyStackException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                s.pop();
            }
        });
    }

    @Test
    public void integersOnly() {
        Stack<Integer> s = new Stack<>();
        s.push(5);
        assertEquals(Integer.valueOf(5), s.pop());
    }

}
