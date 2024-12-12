package dev.ccr.dmscw2024.screens.start;

import dev.ccr.dmscw2024.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartController {
    private Stage stage;
    private Controller controller;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button startButton;

    @FXML
    private Button storyModeButton;

    public void initialise(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;

        backgroundImage.setImage(new Image(String.valueOf(getClass().getResource("/dev/ccr/dmscw2024/images/background/bgstart.jpg"))));
        backgroundImage.setFitWidth(stage.getWidth());
        backgroundImage.setFitHeight(stage.getHeight());

        styleButton(startButton, "#007bff", "#0056b3");
        startButton.setOnAction(e -> controller.levelOne());

        styleButton(storyModeButton, "#fff340", "#999120");
        storyModeButton.setOnAction(e -> controller.showStoryModeScreen());
    }

    private void styleButton(Button button, String defaultColour, String hoverColour) {
        button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + defaultColour + ";-fx-text-fill: black; -fx-background-radius: 20;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + hoverColour + ";-fx-text-fill: black; -fx-background-radius: 20;" +
                        "-fx-cursor: pointer;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + defaultColour + ";-fx-text-fill: black; -fx-background-radius: 10;"
        ));
    }
}
