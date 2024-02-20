package ch.hftm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestSquare {

    @Test
    public void testSquareCreation() {
        Square square = new Square(0, 0);

        assertEquals(0, square.x);
        assertEquals(0, square.y);
        assertFalse(square.occupied);
        assertEquals("A1", square.name);
    }

    @Test
    void testToString() {
        Square square1 = new Square(2, 3);
        Square square2 = new Square(4, 5);

        square1.occupied = true;
        square2.occupied = false;

        assertEquals("Square", square1.toString());
        assertEquals("Square", square2.toString());

        square1.setName("A1");
        square2.setName("H8");

        assertEquals("Square", square1.toString());
        assertEquals("Square", square2.toString());
    }

    @Test
    void testSetName() {
        Square square = new Square(1, 1);
        assertEquals("B2", square.name);

        square.setName("A1");
        assertEquals("A1", square.name);
    }

    @Test
    void testOccupiedStatus() {
        Square square = new Square(3, 4);
        assertFalse(square.occupied);

        square.occupied = true;
        assertTrue(square.occupied);

        square.occupied = false;
        assertFalse(square.occupied);
    }

    // Add more test cases as needed
}