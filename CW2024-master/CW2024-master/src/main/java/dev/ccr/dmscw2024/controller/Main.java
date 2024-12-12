package dev.ccr.dmscw2024.controller;

import java.lang.reflect.InvocationTargetException;

import dev.ccr.dmscw2024.screens.start.Start;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private static Controller myController;

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		myController = new Controller(stage);
		myController.launchGame();
	}

	public static void showStartScreen(Stage stage) {
		Start startScreen = new Start(stage, myController);
		startScreen.display();
	}

	public static void main(String[] args) {
		launch();
	}
}