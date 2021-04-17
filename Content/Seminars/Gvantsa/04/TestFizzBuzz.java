import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFizzBuzz {
    private  FizzBuzz fizzBuzz;

    @BeforeAll
    public  void init(){
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void create(){
        FizzBuzz fizzBuzz = new FizzBuzz();
    }

    @Test
    public void div15(){
        assertEquals("FizzBuzz", fizzBuzz.evaluate(15));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(30));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(150));

        for (int i = 1; i <= 10; i++){
            assertEquals("FizzBuzz", fizzBuzz.evaluate(i * 15));
        }
    }

    @Test
    public void contains35(){
        assertEquals("FizzBuzz", fizzBuzz.evaluate(35));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(37845));
        assertEquals("FizzBuzz", fizzBuzz.evaluate(5354));
    }

    @Test
    public void div3(){
        assertEquals("Fizz", fizzBuzz.evaluate(18));
        assertEquals("Fizz", fizzBuzz.evaluate(21));
        assertEquals("Fizz", fizzBuzz.evaluate(333));
    }

    @Test
    public void contains3(){
        assertEquals("Fizz", fizzBuzz.evaluate(301));
        assertEquals("Fizz", fizzBuzz.evaluate(233));
        assertEquals("Fizz", fizzBuzz.evaluate(1234));
    }

    @Test
    public void div5(){
        assertEquals("Buzz", fizzBuzz.evaluate(50));
        assertEquals("Buzz", fizzBuzz.evaluate(190));
        assertEquals("Buzz", fizzBuzz.evaluate(200));
    }

    @Test
    public void contains5(){
        assertEquals("Buzz", fizzBuzz.evaluate(559));
        assertEquals("Buzz", fizzBuzz.evaluate(652));
        assertEquals("Buzz", fizzBuzz.evaluate(152));
    }

    @Test
    public void others(){
        assertEquals("1", fizzBuzz.evaluate(1));
        assertEquals("17", fizzBuzz.evaluate(17));
        assertEquals("7441", fizzBuzz.evaluate(7441));
    }
}
