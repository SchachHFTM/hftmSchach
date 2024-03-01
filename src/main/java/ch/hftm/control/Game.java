package ch.hftm.control;

import java.util.ArrayList;

import ch.hftm.Square;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Game {
    private Piece[][] board;
    private boolean whiteTurn;

    public Game() {
        whiteTurn = true;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void switchTurn() {
        whiteTurn = !whiteTurn;
    }

    public boolean movePiece(Square sourceSquare, Square destinationSquare, ArrayList<Square> squares) {
        Piece startPiece = sourceSquare.getPiece();

        boolean isWhitePiece = startPiece.getColor() == EColorPiece.WHITE;
        if ((isWhitePiece && !whiteTurn) || (!isWhitePiece && whiteTurn)) {
            return false;
        }

        int destRow = GridPane.getRowIndex(destinationSquare);
        int destCol = GridPane.getColumnIndex(destinationSquare);

        Piece occupyingPiece = destinationSquare.getPiece();
        if (destinationSquare.occupied && occupyingPiece.getColor() == startPiece.getColor()) {
            return false;
        }

        if (occupyingPiece != null) {
            capturePiece(occupyingPiece, startPiece);
            if (occupyingPiece.type.equals("King")) {
                System.out.println("Schach-Matt");
            }
        }

        startPiece.x = destCol;
        startPiece.y = destRow;

        sourceSquare.getChildren().remove(startPiece);
        sourceSquare.setPiece(null);
        sourceSquare.occupied = false;

        GridPane.setColumnIndex(startPiece, destCol);
        GridPane.setRowIndex(startPiece, destRow);
        destinationSquare.getChildren().add(startPiece);
        destinationSquare.setPiece(startPiece);
        destinationSquare.occupied = true;

        startPiece.checkPossibleMoves();
        sourceSquare.setEffect(null);
        switchTurn();

        return true;
    }

    public boolean capturePiece(Piece endPiece, Piece startPiece) {
        if (startPiece.getColor() != endPiece.getColor()) {
            Pane parentPane = (Pane) endPiece.getParent();
            parentPane.getChildren().remove(endPiece);
            return true;
        } else {
            return false;
        }
    }
}
