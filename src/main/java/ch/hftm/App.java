package ch.hftm;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Game"), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setSceneRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String getCurrentFilePath() {
        // If the path is a file path, convert it to a readable path
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File f = new File(path);
        f = f.getParentFile();
        f = f.getParentFile();
        System.out.println();
        path = f.getAbsolutePath().replace(File.separator, "/");
        path = path.replace("%20", " ");
        System.out.println(path);

        // Return the current file path
        return path;
    }

}