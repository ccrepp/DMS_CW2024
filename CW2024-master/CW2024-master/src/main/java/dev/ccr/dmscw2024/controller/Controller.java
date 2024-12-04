package dev.ccr.dmscw2024.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;

import dev.ccr.dmscw2024.screens.Start;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.ccr.dmscw2024.levels.LevelParent;

public class Controller implements PropertyChangeListener {

	private static final String LEVEL_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.Level1";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame()   {
			try {
				showStartScreen();
			}
			catch(Exception e){
				showErrorAlert("FAILED TO LAUNCH: " + e.getMessage());
			}
	}

	public void showStartScreen() {
		Start startScreen = new Start(stage, this);
		startScreen.display();
	}

	public void levelOne() {
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
			System.out.println("Controller: Level Loaded " + myClass.getName());

			Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Stage.class);
			System.out.println("Controller: Level Constructor Loaded " + constructor.getName());

			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), stage);
			System.out.println("Controller: Level Constructing " + myLevel.getClass().getName());

			myLevel.addPropertyChangeListener(this);

			Scene scene = myLevel.initializeScene();

			stage.setScene(scene);
			myLevel.startGame();

			System.out.println("Game Started");

		} catch(Exception e){
			showErrorAlert("FAILED TO LOAD LEVEL: " + className + "\t" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if("levelTransition".equals(evt.getPropertyName())){
			String nextLevelClassName = (String) evt.getNewValue();
			System.out.println("TRANSITIONING TO LEVEL : " + nextLevelClassName);
			goToLevel(nextLevelClassName);
		}
		else{
			showErrorAlert("LEVEL TRANSITION FAILED : " + evt.getNewValue());
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
