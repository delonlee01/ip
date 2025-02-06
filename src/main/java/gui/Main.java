package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import woody.Woody;

/**
 * A GUI for Woody using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane container = fxmlLoader.load();
            Scene scene = new Scene(container);
            scene.getStylesheets().add(Main.class.getResource("/css/main.css").toExternalForm());
            stage.setTitle("Woody");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setWoody(new Woody());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
