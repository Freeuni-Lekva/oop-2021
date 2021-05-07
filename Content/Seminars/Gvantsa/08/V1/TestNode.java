package V1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestNode {

    @Test
    void value() {
        Node n = new Node(5);
        assertEquals(5, n.evaluate());
        assertEquals("(5.0)", n.toString());
    }

    @Test
    void plus() {
        Node n = new Node('+', 5, 7);
        assertEquals(12, n.evaluate());
        assertEquals("((5.0)+(7.0))", n.toString());
    }

    @Test
    void minus() {
        Node n = new Node('-', 5, 7);
        assertEquals(-2, n.evaluate());
        assertEquals("((5.0)-(7.0))", n.toString());
    }

    @Test
    void multiply() {
        Node n = new Node('*', 5, 7);
        assertEquals(35, n.evaluate());
        assertEquals("((5.0)*(7.0))", n.toString());
    }

    @Test
    void divide() {
        Node n = new Node('/', 5, 7);
        assertEquals(5.0 / 7, n.evaluate());
        assertEquals("((5.0)/(7.0))", n.toString());
    }

    @Test
    void complex() {
        Node n = new Node(
                '*',
                new Node('+', 5, 7),
                new Node('-', 1, 2));
        assertEquals(-12, n.evaluate());
        assertEquals("(((5.0)+(7.0))*((1.0)-(2.0)))", n.toString());
    }
}