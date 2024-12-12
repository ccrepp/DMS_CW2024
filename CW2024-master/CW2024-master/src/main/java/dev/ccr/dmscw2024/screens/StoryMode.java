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

public class StoryMode {
    private final Stage stage;
    private final Controller controller;
    private static final String BACKGROUND_IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/hyperspace.jpg";

    public StoryMode(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void display() {

        // Background Image
        ImageView backgroundImage = new ImageView(new Image(String.valueOf(getClass().getResource(BACKGROUND_IMAGE_NAME))));
        backgroundImage.setFitHeight(stage.getHeight());
        backgroundImage.setFitWidth(stage.getWidth());

        // Header
        Text header = new Text("~Story Mode~");
        header.setStyle("-fx-font-size: 40px; -fx-font-weight: bold");

        // VBox Background
        Rectangle VBoxBackground = new Rectangle(400, 400);
        VBoxBackground.setFill(Color.YELLOW);
        VBoxBackground.setArcWidth(20);
        VBoxBackground.setArcHeight(20);

        // VBox with Header AND Buttons
        VBox layout = getVBox(header);

        // VBox Layout
        StackPane VBox = new StackPane(VBoxBackground, layout);
        VBox.setStyle("-fx-alignment: center");

        // Overall Layout
        StackPane root = new StackPane(backgroundImage, VBox);

        Scene storyScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(storyScene);
        stage.show();
    }

    private VBox getVBox(Text header) {
        // Branch Buttons
        Button TPMButton = new Button("The Phantom Menace");
        styleButton(TPMButton, "#ffc107", "#e0a800");
        TPMButton.setOnAction(e -> TPMTransition());

        Button branch2Button = new Button("Attack of the Droids");
        styleButton(branch2Button, "#dc3545", "#c82333");
        branch2Button.setOnAction(e -> AOTCdroidTransition());

        Button branch3Button = new Button("Attack of the Clones");
        styleButton(branch3Button, "#007bff", "#0056b3");
        branch3Button.setOnAction(e -> AOTCcloneTransition());

        // Layout
        VBox layout = new VBox(20, header, TPMButton, branch2Button, branch3Button);
        layout.setStyle("-fx-alignment: center; -fx-padding: 50");
        return layout;
    }

    private void styleButton(Button button, String normalColor, String hoverColor) {
        button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + normalColor + "; -fx-text-fill: black; -fx-background-radius: 10;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + hoverColor + "; -fx-text-fill: black; -fx-background-radius: 20;" +
                        "-fx-cursor: pointer;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; " +
                        "-fx-background-color: " + normalColor + "; -fx-text-fill: black; -fx-background-radius: 10;"
        ));
    }

    private void TPMTransition() {
        Transition transition = new Transition(
                stage,
                "/dev/ccr/dmscw2024/images/TPM/naboo.png",
                "an invasion of Naboo has begun! \n get in your ship and fight back!",
                controller::TPM
        );
        transition.display();
    }

    private void AOTCdroidTransition() {
        Transition transition = new Transition(
                stage,
                "/dev/ccr/dmscw2024/images/AOTC/bg/kaminoencounter.jpeg",
                "now that you've seen the army we've amassed,\n" +
                        "I won't let you go back alive!",
                controller::AOTCdroid
        );
        transition.display();
    }

    private void AOTCcloneTransition() {
        Transition transition = new Transition(
                stage,
                "/dev/ccr/dmscw2024/images/AOTC/bg/CloneArrival.jpg",
                "the Clone army has arrived!",
                controller::AOTCclone
        );
        transition.display();
    }
}

