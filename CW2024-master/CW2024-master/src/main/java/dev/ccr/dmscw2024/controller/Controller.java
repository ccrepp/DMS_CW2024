package dev.ccr.dmscw2024.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.ccr.dmscw2024.levels.LevelParent;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.LevelOne";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame()   {
			try {
				stage.show();
				goToLevel(LEVEL_ONE_CLASS_NAME);
			}
			catch(Exception e){
				showErrorAlert("FAILED TO LAUNCH: " + e.getMessage());
			}
	}

	private void goToLevel(String className)  {
		try{
			System.out.println("Controller: Loading level: " + className);

			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);

			System.out.println("Controller: Constructor found: " + constructor);

			System.out.println("Controller: Constructing... " + constructor);

			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

			System.out.println("Controller: Constructing continues... ");

			myLevel.addObserver(this);

			System.out.println("Controller: more constructing ");

			Scene scene = myLevel.initializeScene();

			System.out.println("Controller: Scene initialised.");

			stage.setScene(scene);
			myLevel.startGame();

			System.out.println("Game Started");

		} catch(Exception e){
			showErrorAlert("FAILED TO LOAD LEVEL: " + className + "\t" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable observable, Object arg) {
		if(arg instanceof String){
			String nextLevelClassName = (String) arg;
			System.out.println("TRANSITIONING TO LEVEL : " + nextLevelClassName);
			goToLevel(nextLevelClassName);
		}
		else{
			showErrorAlert("LEVEL TRANSITION FAILED : " + arg);
		}
	}

	private void showErrorAlert(String message){
		javafx.application.Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("AN ERROR OCCURRED");
			alert.setContentText(message);
			alert.showAndWait();
		});
	}

}
