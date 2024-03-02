package ch.hftm.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.hftm.SaveGame;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import ch.hftm.model.Rook;

class TestSaveGame {
    private SaveGame saveGame;

    @BeforeEach
    void setUp() {
        saveGame = new SaveGame();
    }

    @Test
    void testSetNewGamePiecesPosition() {
        saveGame.setNewGamePiecesPosition();
        Piece[][] pieces = saveGame.getPiecesPosition();
        assertNotNull(pieces);
        assertEquals(2, pieces.length);
        assertEquals(16, pieces[0].length);
        assertEquals(16, pieces[1].length);
        // Check if pieces are initialized correctly
        assertEquals("Rook", pieces[0][0].getClass().getSimpleName());
        assertEquals("Knight", pieces[0][1].getClass().getSimpleName());
        assertEquals("Bishop", pieces[0][2].getClass().getSimpleName());
        assertEquals("Queen", pieces[0][3].getClass().getSimpleName());
        assertEquals("King", pieces[0][4].getClass().getSimpleName());
        assertEquals("Bishop", pieces[0][5].getClass().getSimpleName());
        assertEquals("Knight", pieces[0][6].getClass().getSimpleName());
        assertEquals("Rook", pieces[0][7].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][8].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][9].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][10].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][11].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][12].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][13].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][14].getClass().getSimpleName());
        assertEquals("Pawn", pieces[0][15].getClass().getSimpleName());
        assertEquals("Rook", pieces[1][0].getClass().getSimpleName());
        assertEquals("Knight", pieces[1][1].getClass().getSimpleName());
        assertEquals("Bishop", pieces[1][2].getClass().getSimpleName());
        assertEquals("Queen", pieces[1][3].getClass().getSimpleName());
        assertEquals("King", pieces[1][4].getClass().getSimpleName());
        assertEquals("Bishop", pieces[1][5].getClass().getSimpleName());
        assertEquals("Knight", pieces[1][6].getClass().getSimpleName());
        assertEquals("Rook", pieces[1][7].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][8].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][9].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][10].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][11].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][12].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][13].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][14].getClass().getSimpleName());
        assertEquals("Pawn", pieces[1][15].getClass().getSimpleName());
    }

    @Test
    void testSetAndGetPiecesPosition() {
        Piece[][] testPieces = new Piece[2][16];
        testPieces[0][0] = new Rook(EColorPiece.WHITE, 0, 0);
        testPieces[1][7] = new Rook(EColorPiece.BLACK, 7, 7);
        saveGame.setPiecesPosition(testPieces);
        Piece[][] pieces = saveGame.getPiecesPosition();
        assertNotNull(pieces);
        assertEquals(2, pieces.length);
        assertEquals(16, pieces[0].length);
        assertEquals(16, pieces[1].length);
        assertEquals("Rook", pieces[0][0].getClass().getSimpleName());
        assertEquals("Rook", pieces[1][7].getClass().getSimpleName());
    }

    @Test
    void testSetAndGetPlayerNames() {
        saveGame.setPlayerNameWhite("Alice");
        saveGame.setPlayerNameBlack("Bob");
        assertEquals("Alice", saveGame.getPlayerNameWhite());
        assertEquals("Bob", saveGame.getPlayerNameBlack());
    }

    @Test
    void testSetAndGetCurrentPlayer() {
        saveGame.setCurrentPlayer(true);
        assertTrue(saveGame.getCurrentPlayer());
        saveGame.setCurrentPlayer(false);
        assertFalse(saveGame.getCurrentPlayer());
    }

}