package ch.hftm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ch.hftm.control.Game;
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
    private Stage stage;

    @FXML
    private Button editPlayer1;

    @FXML
    private Button editPlayer2;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button newGameButton;

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

    @FXML
    private GridPane gridPaneBoard;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setSceneRoot("Login");
    }

    @FXML
    public void creatAboard() {
        cb = new Board(gridPaneBoard);
        newGameButton.setDisable(true);
        restartButten.setDisable(false);

    }

    public void loadABoard(SaveGame saveGame) {
        cb = new Board(gridPaneBoard, saveGame);
    }

    @FXML
    public void loadAboard(SaveGame saveGame) {
        cb = new Board(gridPaneBoard, saveGame);
        newGameButton.setDisable(true);
        restartButten.setDisable(false);
    }

    @FXML
    public void newGamebuttonVisible() {
        newGameButton.setDisable(false);
        restartButten.setDisable(true);
    }

    @FXML
    private void saveGame() throws IOException {
        SaveGame save = cb.currentGame;
        //TODO löschen ****test */
        // cb.currentGame.piecesPosition[1][2].x = 4;
        // cb.currentGame.piecesPosition[1][2].y = 4;
        // cb.currentGame.piecesPosition[0][1].y = 3;
        // cb.currentGame.piecesPosition[0][2].y = 3;

        //end Test
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
    private void loadGame() throws IOException {
        String path = App.getCurrentFilePath();
        System.out.println(path);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Save Files", "*.ser"));
        fileChooser.setTitle("Load Game");

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Open File");
            System.out.println(selectedFile.getPath());

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile))) {
                SaveGame load = (SaveGame) in.readObject();
                loadAboard(load);
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
