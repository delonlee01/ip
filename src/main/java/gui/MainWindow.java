package gui;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image woodyImage = new Image(this.getClass().getResourceAsStream("/images/Woody.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Woody instance */
    public void setWoody(Woody w) {
        woody = w;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Woody's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = woody.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWoodyDialog(response, woodyImage)
        );
        userInput.clear();
    }
}
