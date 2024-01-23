package ch.hftm;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GameController {

    @FXML
    private GridPane board;

    private Board cb;

    @FXML
    public void creatABoard(GridPane board) {
        cb = new Board(board);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setSceneRoot("Login");
    }

    @FXML
    private Button Start;

    @FXML
    private Button editPlayer1;

    @FXML
    private Button editPlayer2;

    @FXML
    private Button restartButten;

    @FXML
    private Button saveGameButton;

    @FXML
    private TextField textFieldPlayer1;

    @FXML
    private TextField textFieldPlayer2;

    @FXML
    private Label timePlayer1;

    @FXML
    private Label timePlayer2;

}
