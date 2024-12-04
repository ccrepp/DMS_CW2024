package dev.ccr.dmscw2024.screens;

import dev.ccr.dmscw2024.levels.LevelParent;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.scene.control.Button;

import javafx.stage.Stage;

public class Pause {
    private final Stage stage;
    private final LevelParent levelParent;

    public Pause(Stage stage, LevelParent levelParent) {
        this.stage = stage;
        this.levelParent = levelParent;
    }

    public void display() {
        levelParent.stopGame();

        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50");

        Button resumeButton = new Button("Resume Game");
        resumeButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 10;"
        );

        resumeButton.setOnMouseEntered(e -> resumeButton.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #218838; -fx-text-fill: white; -fx-background-radius: 20;" +
                        "-fx-cursor: pointer;"
        ));

        resumeButton.setOnMouseExited(e -> resumeButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #28a745; -fx-text-fill: white; -fx-background-radius: 10;"
        ));
        resumeButton.setOnAction(e -> {
            stage.setScene(levelParent.getScene());
            levelParent.startGame();
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 10;"
        );

        exitButton.setOnMouseEntered(e -> exitButton.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #c82333; -fx-text-fill: white; -fx-background-radius: 20;" +
                        "-fx-cursor: pointer;"
        ));

        exitButton.setOnMouseExited(e -> exitButton.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 10;"
        ));
        exitButton.setOnAction(e -> {
            levelParent.endGame();
            stage.close();
        });

        layout.getChildren().addAll(resumeButton, exitButton);

        Scene pauseScene = new Scene(layout, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(pauseScene);
        stage.show();
    }
}
