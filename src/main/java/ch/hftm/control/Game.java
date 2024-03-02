package ch.hftm.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import ch.hftm.Board;
import ch.hftm.Square;
import ch.hftm.model.EColorPiece;
import ch.hftm.model.Piece;
import javafx.scene.layout.Pane;

public class Game {
    public static final Logger logger = Logger.getLogger(Game.class.getName());

    static {
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager
                    .readConfiguration(Game.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean whiteTurn;
    private GameController gameController;

    public Game(GameController gameController) {
        this.gameController = gameController;
        whiteTurn = true;
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

        boolean isWhitePiece = startPiece.getColor() == EColorPiece.WHITE;
        if ((isWhitePiece && !whiteTurn) || (!isWhitePiece && whiteTurn)) {
            return false;
        }

        int destRow = destinationSquare.y;
        int destCol = destinationSquare.x;

        Piece occupyingPiece = destinationSquare.getPiece();

        logger.log(Level.INFO,
                "Moving " + startPiece.getColor() + " " + startPiece.getClass().getSimpleName() + " from " +
                        sourceSquare.getName() + " to " + destinationSquare.getName() +
                        (destinationSquare.occupied && occupyingPiece.getColor() != startPiece.getColor()
                                ? " Capture Piece: " + occupyingPiece.type + " from Color: "
                                        + occupyingPiece.getColor()
                                : ""));

        if (destinationSquare.occupied && occupyingPiece.getColor() == startPiece.getColor()) {
            return false;
        }

        if (occupyingPiece != null) {
            capturePiece(occupyingPiece, startPiece);
            if (occupyingPiece.type.equals("King")) {
                logger.log(Level.WARNING, "SCHACH-MATT PLAYER " + sourceSquare.getPiece().getColor() + " HAVE WON");
                gameController.showWonPlayer("GAME OVER: Player " + sourceSquare.getPiece().getColor() + " have won");

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
