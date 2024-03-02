package ch.hftm.model;

import java.util.ArrayList;

import ch.hftm.control.Game;
import ch.hftm.control.SaveGame;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {

    @FXML
    GridPane board;
    public static ArrayList<Square> squares = new ArrayList<>();
    public SaveGame currentGame = new SaveGame();
    public static Game game;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Board(GridPane board, SaveGame currentGameFromSave, Game game) {
        this.board = board;
        this.game = game;
        initializeBoard(this.board);
        setPiecesOnBoard(currentGameFromSave.piecesPosition);
        currentGame = currentGameFromSave;
    }

    public Board(GridPane board, Game game) {
        this.board = board;
        this.game = game;
        initializeBoard(this.board);
        currentGame.setNewGamePiecesPosition();
        setPiecesOnBoard(currentGame.piecesPosition);
    }

    private void initializeBoard(GridPane board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #facf9d;");
                } else {
                    square.setStyle("-fx-background-color: #cd8c3f;");
                }
                square.setPadding(new Insets(1));
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                square.setOnMouseClicked(event -> handlePieceSelection(square));
                board.add(square, col, row);
                squares.add(square);
            }
        }
    }

    private void addPiece(Square sq, Piece pi) {
        sq.getChildren().add(pi);
        sq.occupied = true;
        pi.setImage();
    }

    private void setPiecesOnBoard(Piece[][] piArray) {
        for (int i = 0; i < piArray.length; i++) {
            for (int j = 0; j < piArray[i].length; j++) {
                Piece pi = piArray[i][j];
                int x = pi.getPieceX();
                int y = pi.getPieceY();
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    for (Square square : squares) {
                        if (square.x == x && square.y == y) {
                            square.setPiece(pi);
                            addPiece(square, pi);
                        }
                    }
                }
            }
        }
        switchPlayerBoarder();
    }

    private void updateEventHandlers() {
        for (Square square : squares) {
            square.setOnMouseClicked(event -> {
                if (selectedPiece != null) {
                    handleSquareClick(square);
                } else {
                    handlePieceSelection(square);
                }
            });
        }
    }

    private void handlePieceSelection(Square square) {

        Piece piece = square.getPiece();
        if (piece != null) {
            boolean isWhitePiece = piece.getColor() == EColorPiece.WHITE;
            if ((isWhitePiece && !game.whiteTurn) || (!isWhitePiece && game.whiteTurn)) {
                return;
            }

            ArrayList<String> possibleMoves = piece.checkPossibleMoves(squares);
            for (String move : possibleMoves) {
                for (Square possibleMove : squares) {
                    if (possibleMove.getName().equals(move)) {
                        possibleMove.setEffect(new Glow(0.6));
                    }
                }
            }
            piece.setEffect(new Glow(0.8));
            selectedPiece = piece;
            selectedSquare = square;
            updateEventHandlers();
        }
    }

    private void handleSquareClick(Square square) {
        if (selectedPiece != null && selectedSquare != null) {
            ArrayList<String> possibleMoves = selectedPiece.checkPossibleMoves(squares);
            if (possibleMoves.contains(square.getName())) {
                game.movePiece(selectedSquare, square, squares);
                for (String move : possibleMoves) {
                    for (Square possibleMove : squares) {
                        if (possibleMove.getName().equals(move)) {
                            possibleMove.setEffect(null);
                        }
                    }
                }
                selectedPiece = null;
                selectedSquare = null;
                updateEventHandlers();
            } else {
            }
        }
    }

    public static void switchPlayerBoarder() {
        resetBorderWidth();
        double currentBorderWidth = 1.5;
        boolean currentPlayer = game.isWhiteTurn();
        EColorPiece currentColor;
        if (currentPlayer) {
            currentColor = EColorPiece.WHITE;
        } else {
            currentColor = EColorPiece.BLACK;
        }
        for (Square square : squares) {
            EColorPiece pieceColor = square.getPiece() != null ? square.getPiece().getColor() : null;
            if (pieceColor == currentColor) {
                setBorderWidth(currentBorderWidth, square);
            }
        }
    }

    public static void resetBorderWidth() {
        for (Square square : squares) {

            setBorderWidth(1, square); // Hier 0 setzen, um den Rahmen zu entfernen, oder eine andere Standarddicke
                                       // verwenden
        }
    }

    public static void setBorderWidth(double neueDicke, Square square) {
        square.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(neueDicke))));
    }
}
