package ch.hftm.control;

import ch.hftm.Square;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Game {
    private Piece[][] board;
    private boolean whiteTurn;

    public Piece[][] getBoard() {
        return board;
    }

    public Game(GridPane chessBoard, String theme) {
    }

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
    public Piece selectPiece(int startX, int startY) {
        Piece startPiece = board[startX][startY];
        return startPiece;
    }


    public void handlePieceClick(int row, int col) {
        Piece piece = board[row][col];
        System.out.println(row + "," + col);
        System.out.println(piece);
    }
    // TODO: DESELECT the piece to move

    // TODO: Kill the piece to move
}

