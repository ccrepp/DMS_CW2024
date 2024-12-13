package dev.ccr.dmscw2024.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;

import dev.ccr.dmscw2024.screens.Start;
import dev.ccr.dmscw2024.screens.StoryMode;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.ccr.dmscw2024.levels.LevelParent;

public class Controller implements PropertyChangeListener {

	private static final String LEVEL_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.Level1";
	private static final String TPM_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.TPM.TPM1";
	private static final String AOTCdroid_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.AOTCdroid.AOTCd1";
	private static final String AOTCclone_ONE_CLASS_NAME = "dev.ccr.dmscw2024.levels.AOTCclone.AOTCc1";


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

	public void showStoryModeScreen() {
		StoryMode storyModeScreen = new StoryMode(stage, this);
		storyModeScreen.display();
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

	public void TPM() {
		try {
			stage.show();
			goToLevel(TPM_ONE_CLASS_NAME);
		}
		catch(Exception e){
			showErrorAlert("FAILED TO LAUNCH: " + e.getMessage());
		}
	}

	public void AOTCdroid() {
		try {
			stage.show();
			goToLevel(AOTCdroid_ONE_CLASS_NAME);
		}
		catch(Exception e){
			showErrorAlert("FAILED TO LAUNCH: " + e.getMessage());
		}
	}

	public void AOTCclone() {
		try {
			stage.show();
			goToLevel(AOTCclone_ONE_CLASS_NAME);
		}
		catch(Exception e){
			showErrorAlert("FAILED TO LAUNCH: " + e.getMessage());
		}
	}

	private void goToLevel(String levelName)  {
		try{
			System.out.println("Controller: Loading level: " + levelName);
			Class<?> levelClass = Class.forName(levelName);
			System.out.println("Controller: Level Loaded " + levelClass.getName());

			LevelParent level = null;
			
			Constructor<?>[] constructors = levelClass.getConstructors();
			Constructor<?> levelClassConstructor = null;

			for (Constructor<?> constructor : constructors) {
				if (constructor.getParameterCount() == 4) {
					levelClassConstructor = constructor;
					level = (LevelParent) levelClassConstructor.newInstance(stage.getHeight(), stage.getWidth(), stage, this);
					System.out.println("Controller: Level Constructor Loaded " + levelClassConstructor.getName());
					break;
				}
				else if (constructor.getParameterCount() == 3) {
					levelClassConstructor = constructor;
					level = (LevelParent) levelClassConstructor.newInstance(stage.getHeight(), stage.getWidth(), stage);
					System.out.println("Controller: Level Constructor Loaded " + levelClassConstructor.getName());
					break;
				}
			}
			
			if (levelClassConstructor == null) {
				throw new Exception("No suitable constructor found for level: " + levelName);
			}

			level.addPropertyChangeListener(this);
			Scene levelScene = level.initializeScene();
			stage.setScene(levelScene);
			level.startGame();


			System.out.println("Game Started");

		} catch(Exception e){
			showErrorAlert("FAILED TO LOAD LEVEL: " + levelName + "\t" + e.getMessage());
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
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("AN ERROR OCCURRED");
			alert.setContentText(message);
			alert.showAndWait();
		});
	}

}
