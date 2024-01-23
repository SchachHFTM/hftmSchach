package ch.hftm;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {

    @FXML
    GridPane board;
    private Color colorBlack = Color.web("#cd8c3f");
    private Color colorWithe = Color.web("#facf9d");
    public ArrayList<Square> squares = new ArrayList<>();

    public Board(GridPane board) {
        this.board = board;
        initializeBoard(this.board, colorBlack, colorWithe);
    }

    private void initializeBoard(GridPane board, Color colorBlack, Color colorWithe) {
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

}
