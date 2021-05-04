import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcBrainTest {

    @Test
    void newBrain() {
        CalcBrain brain = new CalcBrain();
        assertEquals("0", brain.getDisplay());
    }

    @Test
    void integers() {
        CalcBrain brain = new CalcBrain();
        brain.pushDigit('1');
        brain.pushDigit('2');
        assertEquals("012", brain.getDisplay());
    }
}