package ch.hftm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestCoordinates {

    @Test
    void testCoordinateConversion() {
        // Test a few sample coordinates
        assertEquals("A1", Coordinates.fromCoordinatesToNotation(0, 0));
        assertEquals("A2", Coordinates.fromCoordinatesToNotation(1, 0));
        assertEquals("B1", Coordinates.fromCoordinatesToNotation(0, 1));
        assertEquals("H8", Coordinates.fromCoordinatesToNotation(7, 7));

        // Test conversion from notation to coordinates
        assertEquals(0, Coordinates.fromNotationToX("A1"));
        assertEquals(0, Coordinates.fromNotationToY("A1"));
        assertEquals(1, Coordinates.fromNotationToX("A2"));
        assertEquals(0, Coordinates.fromNotationToY("A2"));
    }

    @Test
    void testConvertToNotation() {
        // Test conversion of coordinates to notation
        assertEquals("A1", Coordinates.convertToNotation(0, 0));
        assertEquals("H8", Coordinates.convertToNotation(7, 7));
    }

    @Test
    void testFromCoordinatesToNo() {
        // Test conversion from coordinates to enum constant
        assertEquals(Coordinates.A1, Coordinates.fromCoordinatesToNo(0, 0));
        assertEquals(Coordinates.H8, Coordinates.fromCoordinatesToNo(7, 7));
    }

    @Test
    void testFromNotation() {
        // Test conversion from notation to enum constant
        assertEquals(Coordinates.A1, Coordinates.fromNotation("A1"));
        assertEquals(Coordinates.H8, Coordinates.fromNotation("H8"));
    }

    @Test
    void testFromNotationToX() {
        // Test conversion from notation to x coordinate
        assertEquals(0, Coordinates.fromNotationToX("A1"));
        assertEquals(7, Coordinates.fromNotationToX("H8"));
    }

    @Test
    void testFromNotationToY() {
        // Test conversion from notation to y coordinate
        assertEquals(0, Coordinates.fromNotationToY("A1"));
        assertEquals(7, Coordinates.fromNotationToY("H8"));
    }

    @Test
    void testInvalidCoordinates() {
        // Test invalid coordinates
        assertThrows(IllegalArgumentException.class, () -> Coordinates.fromCoordinatesToNotation(8, 8));
        assertThrows(IllegalArgumentException.class, () -> Coordinates.fromCoordinatesToNotation(-1, -1));
    }

    @Test
    void testInvalidNotation() {
        // Test invalid notation
        assertThrows(IllegalArgumentException.class, () -> Coordinates.fromNotationToX("I1"));
        assertThrows(IllegalArgumentException.class, () -> Coordinates.fromNotationToY("A9"));
    }
}
