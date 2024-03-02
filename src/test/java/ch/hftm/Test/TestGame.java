package ch.hftm.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.hftm.GameController;
import ch.hftm.Square;
import ch.hftm.Mock.MockPiece;
import ch.hftm.control.Game;
import ch.hftm.model.EColorPiece;
import javafx.scene.layout.Pane;

public class TestGame {

    private Game game;
    private GameController gameController;
    private ArrayList<Square> squares;

    @BeforeEach
    public void setUp() {
        gameController = new GameController();
        game = new Game(gameController);
        squares = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares.add(new Square(i, j));
            }
        }
    }

    @Test
    public void testStartGame() {
        game.startGame();
        assertTrue(game.isWhiteTurn());
        assertEquals(10, game.whiteRemainingMinutes);
        assertEquals(10, game.blackRemainingMinutes);
    }

    /*
     * @Test
     * public void testMovePiece() {
     * Piece piece = new MockPiece(EColorPiece.WHITE, 0, 0);
     * squares.get(1).setPiece(piece);
     * 
     * assertTrue(game.movePiece(squares.get(1), squares.get(9), squares));
     * 
     * assertNull(squares.get(1).getPiece());
     * 
     * assertEquals(piece, squares.get(9).getPiece());
     * }
     */

    @Test
    public void testCapturePiece() {
        Square sourceSquare = new Square(0, 0);
        MockPiece startPiece = new MockPiece(EColorPiece.WHITE, 0, 0);
        sourceSquare.setPiece(startPiece);

        Square destinationSquare = new Square(1, 1);
        MockPiece endPiece = new MockPiece(EColorPiece.BLACK, 1, 1);
        destinationSquare.setPiece(endPiece);

        Pane parentPane = new Pane();
        parentPane.getChildren().add(endPiece);

        assertTrue(game.capturePiece(endPiece, startPiece));
    }

}