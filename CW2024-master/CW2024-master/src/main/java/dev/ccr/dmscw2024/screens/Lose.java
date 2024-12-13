package dev.ccr.dmscw2024.screens;

import dev.ccr.dmscw2024.controller.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Lose - game lose screen
 */
public class Lose {
    private final Stage stage;

    /**
     * Lose constructor
     * @param stage primary stage on which screen is displayed
     */
    public Lose(Stage stage) {
        this.stage = stage;
    }

    /**
     * displays lose screen
     */
    public void display() {
        // Background Image
        ImageView backgroundImage = new ImageView(new Image(String.valueOf(getClass().getResource("/dev/ccr/dmscw2024/images/default/youlose.jpg"))));
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setFitWidth(stage.getWidth());

        // Message
        Text message = new Text("You Lose!");
        message.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-fill: red;");

        // Translucent Background
        Rectangle messageBackground = new Rectangle(400, 400);
        messageBackground.setFill(Color.rgb(0, 0, 0, 0.7));
        messageBackground.setArcWidth(20);
        messageBackground.setArcHeight(20);

        // Layout for Message and Buttons
        Button mainMenuButton = new Button("Main Menu");
        styleButton(mainMenuButton, "#007bff", "#0056b3");
        mainMenuButton.setOnAction(e -> Main.showStartScreen(stage));

        Button exitButton = new Button("Exit Game");
        styleButton(exitButton, "#dc3545", "#c82333");
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20, message, mainMenuButton, exitButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        StackPane messageContainer = new StackPane(messageBackground, layout);
        messageContainer.setStyle("-fx-alignment: center;");

        // Root Layout
        StackPane root = new StackPane(backgroundImage, messageContainer);

        Scene winScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(winScene);
        stage.show();
    }

    private void styleButton(Button button, String normalColor, String hoverColor) {
        button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + normalColor + "; -fx-text-fill: white; -fx-background-radius: 10;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + hoverColor + "; -fx-text-fill: white; -fx-background-radius: 20;" +
                        "-fx-cursor: pointer;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + normalColor + "; -fx-text-fill: white; -fx-background-radius: 10;"
        ));
    }
}
