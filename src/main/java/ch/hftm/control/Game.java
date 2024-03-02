package ch.hftm.control;

import java.util.ArrayList;
import java.util.logging.Logger;

import ch.hftm.Board;
import ch.hftm.GameController;
import ch.hftm.Square;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.layout.Pane;

public class Game {
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    public boolean whiteTurn;
    public int whiteRemainingMinutes;
    public int blackRemainingMinutes;
    private GameController gameController;

    public Game(GameController gameController) {
        this.gameController = gameController;
        whiteTurn = true;
    }

    public void startGame() {
        whiteRemainingMinutes = 10;
        blackRemainingMinutes = 10;

        gameController.startTimerThread(true);
        gameController.startTimerThread(false);
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void switchTurn() {

        whiteTurn = !whiteTurn;
        Board.switchPlayerBoarder();
    }

    public boolean movePiece(Square sourceSquare, Square destinationSquare, ArrayList<Square> squares) {
        Piece startPiece = sourceSquare.getPiece();

        logger.info("Moving " + startPiece.getColor() + " " + startPiece.getClass().getSimpleName() + " from " +
                sourceSquare.getName() + " to " + destinationSquare.getName());

        boolean isWhitePiece = startPiece.getColor() == EColorPiece.WHITE;
        if ((isWhitePiece && !whiteTurn) || (!isWhitePiece && whiteTurn)) {
            return false;
        }

        int destRow = destinationSquare.y;
        int destCol = destinationSquare.x;

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

        destinationSquare.getChildren().add(startPiece);
        destinationSquare.setPiece(startPiece);
        destinationSquare.occupied = true;

        startPiece.checkPossibleMoves(squares);
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
