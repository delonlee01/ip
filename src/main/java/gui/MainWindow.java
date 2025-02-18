package gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import woody.Woody;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Woody woody;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image woodyImage = new Image(this.getClass().getResourceAsStream("/images/Woody.png"));

    /**
     * Initializes the application.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(DialogBox.getWoodyDialog(
                "Howdy partner! I'm Woody\nWhat can I do for you?", woodyImage));
    }

    /**
     * Injects the Woody instance
     */
    public void setWoody(Woody woody) {
        this.woody = woody;
    }

    private void handleWoodyResponse(String input) {
        String response = this.woody.getResponse(input);
        assert !response.isEmpty() : "Woody's response should not be empty";

        this.dialogContainer.getChildren().add(DialogBox.getWoodyDialog(response, woodyImage));
        if (response.startsWith("Bye")) {
            // Solution below inspired by https://stackoverflow.com/a/21996863
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 500);
        }
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        this.handleWoodyResponse(input);
        userInput.clear();
    }
}
