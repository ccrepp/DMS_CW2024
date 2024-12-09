package dev.ccr.dmscw2024.screens;

import dev.ccr.dmscw2024.controller.Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.scene.control.Button;

import javafx.stage.Stage;

public class Start {
    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/bgstart.jpg";


    public Start(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void display() {

        // Background Image
        ImageView backgroundImage= new ImageView(new Image(String.valueOf(getClass().getResource(BACKGROUND_IMAGE_NAME))));
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setFitWidth(stage.getWidth());

        // Header
        Text header = new Text("~Sky Battle~");
        header.setStyle("-fx-font-size: 40px; -fx-font-weight: bold");

        // VBox Background
        Rectangle VBoxBackground = new Rectangle(300, 400);
        VBoxBackground.setFill(Color.rgb(5, 227, 247, 0.5));
        VBoxBackground.setArcWidth(20);
        VBoxBackground.setArcHeight(20);

        // VBox with Header AND Button
        VBox layout = getVBox(header);

        // VBox Layout
        StackPane VBox = new StackPane(VBoxBackground, layout);
        VBox.setStyle("-fx-alignment: center");

        // Overall Layout
        StackPane root = new StackPane(backgroundImage, VBox);

        Scene startScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(startScene);
        stage.show();
    }

    private VBox getVBox(Text header) {
        // Start Button
        Button startButton = new Button("Start Game");
        styleButton(startButton, "#007bff", "#0056b3");
        startButton.setOnAction(e -> controller.levelOne());

        // Story Mode Button
        Button storyModeButton = new Button("Story Mode");
        styleButton(storyModeButton, "#fff340", "#999120");
        storyModeButton.setOnAction(e -> controller.showStoryModeScreen());

        // Layout
        VBox layout = new VBox(20, header, startButton, storyModeButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50");
        return layout;
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
