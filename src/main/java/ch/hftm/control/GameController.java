package ch.hftm.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

import ch.hftm.App;
import ch.hftm.model.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GameController {

    private Board cb;
    private Stage stage;
    private Game game;
    private String tempPlayerWhite;
    private String tempPlayerBlack;

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
    private GridPane gridPaneBoard;

    @FXML
    public Text gameOver;

    @FXML
    public void showWonPlayer(String message) {
        gameOver.setText(message);
        gameOver.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gameOver.setVisible(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // This method is started with the "New Game" button. It loads a default new
    // game.
    @FXML
    public void creatAboard() {
        game = new Game(this);
        cb = new Board(gridPaneBoard, game);
        gameOver.setVisible(false);
        newGameButton.setDisable(true);
        restartButten.setDisable(false);
        Game.logger.log(Level.INFO, "NEW GAME");

    }

    public void loadABoard(SaveGame saveGame) {
        game = new Game(this);
        cb = new Board(gridPaneBoard, saveGame, game);
        gameOver.setVisible(false);
        textFieldPlayer1.setText("White Player");
        textFieldPlayer2.setText("Black Player");

    }

    // This method is triggered in the LoadGame mehtode and creates a new board with
    // the transferred savegame
    @FXML
    public void loadAboard(SaveGame saveGame) {
        game = new Game(this);
        cb = new Board(gridPaneBoard, saveGame, game);
        gameOver.setVisible(false);
        newGameButton.setDisable(true);
        restartButten.setDisable(false);
        textFieldPlayer1.setText(cb.currentGame.getPlayerNameWhite());
        textFieldPlayer2.setText(cb.currentGame.getPlayerNameBlack());

    }

    /// This method is used by the restart button and is just a backup to make sure
    /// you don't accidentally reset the game.
    @FXML
    public void newGamebuttonVisible() {
        newGameButton.setDisable(false);
        restartButten.setDisable(true);
    }

    // the SaveGame method. Saves the current SaveGame class in a serializable file
    // To do this,first open a Filechosser menu to define the path and file name.
    // Then the SaveGame class is saved there
    @FXML
    private void saveGame() throws IOException {
        try {
            // Check if cb is null
            if (cb == null) {
                throw new NullPointerException("cb is null");
            }

            SaveGame save = cb.currentGame;

            if (tempPlayerBlack != null || tempPlayerWhite != null) {
                save.setPlayerNameWhite(tempPlayerWhite);
                save.setPlayerNameBlack(tempPlayerBlack);
            }

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
            Game.logger.log(Level.INFO, "SAVE GAME: " + selectedFile);
        } catch (NullPointerException e) {

            System.err.println("Error: cb is null. Unable to save game.");
            e.printStackTrace();

        } catch (IOException e) {

            System.err.println("Error: Unable to save game due to IO Exception.");
            e.printStackTrace();
            //
        } catch (Exception e) {

            System.err.println("Error: An unexpected error occurred while saving the game.");
            e.printStackTrace();

        }
    }

    // the loadGame method.Loads a SaveGame class from a serializable file.First a
    // Filechosser menu is opened to get the path and file.
    // Then the SaveGame class is given to the LoadAboard() method to load the game
    // board

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
                Game.logger.log(Level.INFO, "LOAD A GAME: " + selectedFile);
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // This 2 Method Save the GamePlayer name Direkt in to the SaveGame Classe. But
    // is cb is null. Than save the NAme in a Temp Variable oft the GameController.
    // By the SaveGame Methode, we Check is the TempVariabl != Null. it's that true.
    // WE Save this TemVariable Name in the SaveGame class

    @FXML
    private void SaveOrEditNameWhite() throws IOException {

        if (editPlayer1.getText().equals("Save")) {
            editPlayer1.setText("Edit");
            textFieldPlayer1.setDisable(true);
        } else {
            editPlayer1.setText("Save");
            textFieldPlayer1.setDisable(false);
        }

        String playerWhite = textFieldPlayer1.getText();

        try {
            if (cb == null) {
                throw new NullPointerException("cb is null");
            }
            cb.currentGame.setPlayerNameWhite(playerWhite);

        } catch (NullPointerException e) {
            tempPlayerWhite = playerWhite;

        }

    }

    @FXML
    private void SaveOrEditNameBlack() throws IOException {

        if (editPlayer2.getText().equals("Save")) {
            editPlayer2.setText("Edit");
            textFieldPlayer2.setDisable(true);
        } else {
            editPlayer2.setText("Save");
            textFieldPlayer2.setDisable(false);
        }
        String playerBlack = textFieldPlayer2.getText();
        try {
            if (cb == null) {
                throw new NullPointerException("cb is null");
            }
            cb.currentGame.setPlayerNameBlack(playerBlack);

        } catch (NullPointerException e) {
            tempPlayerBlack = playerBlack;
        }
    }

}
