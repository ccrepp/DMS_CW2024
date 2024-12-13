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

/**
 * Transition - game transition screen
 */
public class Transition {
    private final Stage stage;
    private final String backgroundImagePath;
    private final String transitionMessage;
    private final Runnable onContinue;

    private Scene currentScene;

    /**
     * Transition constructor
     * @param stage primary stage that transition is displayed on
     * @param backgroundImagePath path to background image for transition
     * @param transitionMessage message to be displayed on trainsiton screen
     * @param onContinue executes {@link Runnable} when user {@code proceed}
     */
    public Transition(Stage stage, String backgroundImagePath, String transitionMessage, Runnable onContinue) {
        this.stage = stage;
        this.backgroundImagePath = backgroundImagePath;
        this.transitionMessage = transitionMessage;
        this.onContinue = onContinue;
    }

    /**
     * displays transition screen
     */
    public void display() {
//      Background Image
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
        currentScene = new Scene(layout, stage.getWidth(), stage.getHeight());
        currentScene.setOnKeyPressed(this::handleInput);
        currentScene.setOnMouseClicked(e -> proceed());

        stage.setScene(currentScene);
        stage.show();

    }

    /**
     * handles any and every key input by user
     * @param e any form of key input
     */
    private void handleInput(KeyEvent e) {
        proceed();
    }

    /**
     * proceeds when called, clearing key input and mouse clicks
     */
    private void proceed() {
        if (onContinue != null) {
            if (currentScene != null) {
                currentScene.setOnKeyPressed(null);
                currentScene.setOnMouseClicked(null);
            }
            onContinue.run();
        }
    }
}
