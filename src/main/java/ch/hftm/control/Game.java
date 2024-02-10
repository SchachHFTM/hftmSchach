package ch.hftm.control;

import java.util.ArrayList;

import ch.hftm.Square;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.layout.GridPane;

public class Game {
    private Piece[][] board;
    private boolean whiteTurn;

    public Piece[][] getBoard() {
        return board;
    }
    
    public Game(GridPane chessBoard, String theme) {

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        Piece startPiece = board[startX][startY];

        if (startPiece == null || startPiece.getColor() != EColorPiece.WHITE) {
            // No piece at the starting position or it's not the player's turn
            return false;
        }

        ArrayList<String> possibleMoves = startPiece.possibleMoves;
        Square destination = new Square(endX, endY);
        if (!possibleMoves.contains(destination)) {
            return false;
        }
        return true;

    }

    // TODO: SELECT the pieces to move
    public Square selectSquare(int startX, int startY) {
        Piece startPiece = board[startX][startY];
    }

    // TODO: DESELECT the piece to move

    // TODO: Kill the piece to move

}
