package dev.ccr.dmscw2024.screens;

import javafx.geometry.Pos;
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

//    private final StackPane layout;
//    private final Text messageText;

    private Scene currentScene;

    public Transition(Stage stage, String backgroundImagePath, String transitionMessage, Runnable onContinue) {
        this.stage = stage;
        this.backgroundImagePath = backgroundImagePath;
        this.transitionMessage = transitionMessage;
        this.onContinue = onContinue;

//        this.layout = new StackPane();
//        layout.setStyle("-fx-alignment: center;");
//        layout.setAlignment(Pos.CENTER);
//
//        this.currentScene = new Scene(layout, stage.getWidth(), stage.getHeight());
//        currentScene.setOnKeyPressed(this::handleInput);
//        currentScene.setOnMouseClicked(this::handleInput);
//
//        this.messageText = new Text(); // instantiate but don't set content yet
//        this.messageText.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-fill: white;");
//
//        Rectangle messageBackground = new Rectangle(800, 200);
//        messageBackground.setFill(Color.rgb(5, 227, 247, 0.5));
//        messageBackground.setArcWidth(20);
//        messageBackground.setArcHeight(20);
//
//        StackPane messageLayout = new StackPane(messageBackground, messageText);
//        messageLayout.setAlignment(Pos.CENTER); // set alignment on message pane
//        messageLayout.setStyle("-fx-alignment: center;");
//
//        layout.getChildren().add(messageLayout);
//        stage.setScene(currentScene);
    }

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
//        Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());
//        stage.setScene(scene);
//        stage.show();

//        if (currentScene == null) {
            currentScene = new Scene(layout, stage.getWidth(), stage.getHeight());
            currentScene.setOnKeyPressed(this::handleInput);
            currentScene.setOnMouseClicked(e -> proceed());
//        }

        stage.setScene(currentScene);
        stage.show();

//        // Input Handling
//        scene.setOnKeyPressed(this::handleInput);
//        scene.setOnMouseClicked(e -> proceed());
    }

    private void handleInput(KeyEvent e) {
        proceed();
    }

    private void handleInput(javafx.scene.input.MouseEvent e) {
        proceed();
    }

    private void proceed() {
        System.out.println("Proceed called!");

        if (onContinue != null) {
//            Scene scene = stage.getScene();
            if (currentScene != null) {
                currentScene.setOnKeyPressed(null);
                currentScene.setOnMouseClicked(null);
            }
//            stage.getScene().setOnKeyPressed(null);
//            stage.getScene().setOnMouseClicked(null);

            onContinue.run();
        }
    }
}
