package ch.hftm.control;

import ch.hftm.Board;
import ch.hftm.SaveGame;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.layout.GridPane;

public class Game {
    private Piece[][] board;
    private boolean whiteTurn;

    public Game(GridPane chessBoard, String theme) {
        this.board = SaveGame.startPiecesPos();
        this.whiteTurn = true;
    }


    public boolean movePiece(int startX, int startY, int endX, int endY){
        Piece startPiece = board[startX][startY];

        if (startPiece == null || startPiece.getColor() != EColorPiece.WHITE) {
            // No piece at the starting position or it's not the player's turn
            return false;
        }

        // Check if the move is valid for the piece
        if (!startPiece.checkPossibleMoves(startX, startY, endX, endY, board)) {
            return false;
        }

        // Check if there's a piece at the destination
        Piece endPiece = board[endX][endY];
        if (endPiece != null) {
            // Piece captured
            // Handle piece capture here
        }

        // Move the piece
        board[endX][endY] = startPiece;
        board[startX][startY] = null;

        // Switch turns
        whiteTurn = !whiteTurn;

        return true;
    }
    
}
