import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {

    MyStack<String> ms;

    @BeforeEach
    public void init(){
        ms = new MyStack<>();
    }

    @Test
    public void sizeEmpty(){
        assertEquals(0, ms.size());
    }

    @Test
    public void sizeOneElement(){
        ms.push("one");
        assertEquals(1, ms.size());
    }

    @Test
    public void sizeManyElement(){
        for (int i = 0; i <= 100; i++)
            ms.push("one" + i);
        assertEquals(101, ms.size());
    }

    @Test
    public void pushOne(){
        String elem = "Element";
        ms.push(elem);
        String actualElem = ms.pop();
        assertEquals(elem, actualElem);
    }

    @Test
    public void popEmpty(){
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                String actualElem = ms.pop();
            }
        });
    }

    @Test
    public void addAll(){
        ms.addAll(new ArrayList<String>(Arrays.asList("1", "2", "3")));
        assertEquals(3, ms.size());
        assertEquals("3", ms.pop());
    }
}
