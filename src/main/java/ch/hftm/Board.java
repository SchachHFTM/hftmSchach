package ch.hftm;

import java.util.ArrayList;

import ch.hftm.model.Piece;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Board {

    @FXML
    GridPane board;
    public ArrayList<Square> squares = new ArrayList<>();
    public SaveGame currentGame = new SaveGame();

    public Board(GridPane board, SaveGame currentGame) {
        this.board = board;
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
                //TODO getSquarbyName hier einbauen
                for (Square square : squares) {
                    if (square.x == x && square.y == y) {
                        addPiece(square, pi);
                    }
                }

            }
        }
    }

}
