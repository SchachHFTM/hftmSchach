package ch.hftm;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button loadFileButton;

    @FXML
    private Button toGameButton;

    @FXML
    private void switchToGame() throws IOException {
        App.setSceneRoot("Game");
    }

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
        }
    }

}
