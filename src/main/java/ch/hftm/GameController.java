package ch.hftm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameController {

    private static GameController instance;
    private Board cb;
    private SaveGame saveGame;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private GridPane gridPaneBoard;

    @FXML
    public void creatABoard() {
        // cb = new Board(gridPaneBoard);
        if (gridPaneBoard != null) {
            cb = new Board(gridPaneBoard);
        } else {
            System.out.println("gridPaneBoard is null");
        }

    }

    public void loadABoard(SaveGame saveGame) {
        cb = new Board(gridPaneBoard, saveGame);

    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setSceneRoot("Login");
    }

    @FXML
    private void saveGameButton() throws IOException {
        SaveGame save = cb.currentGame;
        String path = App.getCurrentFilePath();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Save Files", "*.ser"));
        fileChooser.setInitialFileName("mySavedGame.ser");
        fileChooser.setTitle("Save Game");
        File selectedFile = fileChooser.showSaveDialog(stage);
        FileOutputStream fileOut = new FileOutputStream(selectedFile);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(save);
        out.close();

    };

    @FXML
    private Button Start;

    @FXML
    private Button editPlayer1;

    @FXML
    private Button editPlayer2;

    @FXML
    private Button restartButten;

    @FXML
    private TextField textFieldPlayer1;

    @FXML
    private TextField textFieldPlayer2;

    @FXML
    private Label timePlayer1;

    @FXML
    private Label timePlayer2;

}
