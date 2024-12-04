package dev.ccr.dmscw2024.screens;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Transition {
    private final Stage stage;
    private final String backgroundImagePath;
    private final String transitionMessage;
    private final Runnable onContinue;

    public Transition(Stage stage, String backgroundImagePath, String transitionMessage, Runnable onContinue) {
        this.stage = stage;
        this.backgroundImagePath = backgroundImagePath;
        this.transitionMessage = transitionMessage;
        this.onContinue = onContinue;
    }

    public void display() {
        // Background Image
        ImageView backgroundImage = new ImageView(new Image(String.valueOf(getClass().getResource(backgroundImagePath))));
        backgroundImage.setFitWidth(stage.getWidth());
        backgroundImage.setFitHeight(stage.getHeight());

        // Transition Message
        Text messageText = new Text(transitionMessage);
        messageText.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-fill: white;");

        // Message Background
        Rectangle messageBackground = new Rectangle(800, 200);
        messageBackground.setFill(Color.rgb(5, 227, 247, 0.5));
        messageBackground.setArcWidth(20);
        messageBackground.setArcHeight(20);

        // Message Layout
        StackPane messageLayout = new StackPane(messageBackground, messageText);
        messageLayout.setStyle("-fx-alignment: center;");

        // Layout
        StackPane layout = new StackPane(backgroundImage, messageLayout);
        layout.setStyle("-fx-alignment: center;");

        // Scene
        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.show();

        // Input Handling
        scene.setOnKeyPressed(this::handleInput);
        scene.setOnMouseClicked(e -> proceed());
    }

    private void handleInput(KeyEvent e) {
        proceed();
    }

    private void proceed() {
        if (onContinue != null) {
            onContinue.run();
        }
    }
}
