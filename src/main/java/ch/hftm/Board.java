package ch.hftm;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {

    @FXML
    private GridPane board;
    private Color colorBlack = Color.web("#cd8c3f");
    private Color colorWithe = Color.web("#facf9d");
    public ArrayList<Square> squares = new ArrayList<>();

    public Board(GridPane board) {
        this.board = board;

        initializeBoard(this.board, colorBlack, colorWithe);

    }

    private void initializeBoard(GridPane board, Color colorBlack, Color colorWithe) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);

                // Ändern Sie die Hintergrundfarbe basierend auf der Position
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: " + colorWithe + ";");
                } else {
                    square.setStyle("-fx-background-color: " + colorBlack + ";");
                }

                board.add(square, col, row);
                squares.add(square);
            }
        }
    }

}
