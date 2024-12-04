package dev.ccr.dmscw2024.screens;

import dev.ccr.dmscw2024.controller.Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.control.Button;

import javafx.stage.Stage;

public class Start {
    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/backgroundstart.jpg";


    public Start(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void display() {
        System.out.println("Start: Image URL: " + getClass().getResource(BACKGROUND_IMAGE_NAME));

        // Background Image
        ImageView backgroundImage= new ImageView(new Image(String.valueOf(getClass().getResource(BACKGROUND_IMAGE_NAME))));
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setFitWidth(stage.getWidth());

        // Header
        Text header = new Text("~Sky Battle~");
        header.setStyle("-fx-font-size: 40px; -fx-font-weight: bold");

        // Start Button
        VBox layout = getVBox(header);

        StackPane root = new StackPane(backgroundImage, layout);

//        layout.getChildren().addAll(header, startButton);

        Scene startScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(startScene);
        stage.show();
    }

    private VBox getVBox(Text header) {
        Button startButton = new Button("Start Game");
        startButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #007bff; -fx-text-fill: white; -fx-background-radius: 10;"
        );
        startButton.setOnMouseEntered(e -> startButton.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #0056b3; -fx-text-fill: white; -fx-background-radius: 20;" +
                "-fx-cursor: pointer;"
        ));
        startButton.setOnMouseExited(e -> startButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #007bff; -fx-text-fill: white; -fx-background-radius: 10;"
        ));
        startButton.setOnAction(e -> controller.levelOne());

        // Layout
        VBox layout = new VBox(20, header, startButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50");
        return layout;
    }
}
