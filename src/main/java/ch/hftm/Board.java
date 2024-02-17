package ch.hftm;

import ch.hftm.control.Game;
import ch.hftm.model.Piece;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board extends GridPane {

    @FXML
    GridPane board;
    public ArrayList<Square> squares = new ArrayList<>();
    public SaveGame currentGame = new SaveGame();

    public Game game;

    public Board(GridPane board, SaveGame currentGame) {
        this.board = board;
        this.game = new Game(board, "");
        initializeBoard(this.board);
        setPiecesOnBoard(currentGame.piecesPosition);
    }

    public Board(GridPane board) {
        this.board = board;
        initializeBoard(this.board);
        currentGame.setNewGamePiecesPosition();
        setPiecesOnBoard(currentGame.piecesPosition);
    }

    private void initializeBoard(GridPane board) {
        // Set all Square for the GridPane with Coordiates
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                // Set the Chess Color Background
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #facf9d;");
                } else {
                    square.setStyle("-fx-background-color: #cd8c3f;");
                }
                square.setOnMouseClicked(e -> addPieceClickListener(square));
                board.add(square, col, row, 1, 1);
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
                            square.setPiece(pi); // Associate the piece with the square
                            addPiece(square, pi);
                        }
                    }
                }
            }
        }
    }

    private void addPieceClickListener(Square square) {
        Piece piece = square.getPiece();
        if (piece != null) {
            System.out.println("Selected piece: " + piece + " " + piece.getPieceY());
        } else {
            System.out.println("No piece on this square.");
        }
    }
}
