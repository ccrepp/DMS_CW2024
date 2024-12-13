package dev.ccr.dmscw2024.levels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import dev.ccr.dmscw2024.controller.Controller;
import dev.ccr.dmscw2024.screens.Lose;
import javafx.animation.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.function.Supplier;
import java.util.*;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.interfaces.GameStartEnd;
import dev.ccr.dmscw2024.interfaces.InitialiseActors;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.projectile.ProjectileManager;
import dev.ccr.dmscw2024.utility.*;


/**
 * Level Parent - abstract class for level initialisation, management and updates
 */
public abstract class LevelParent implements GameStartEnd, InitialiseActors {
	// Property Change Support replaced Observers
	private final PropertyChangeSupport support;

	// Level Configuration Variables
	private final double screenHeight;
	private final double screenWidth;
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;

	// Gameplay Variables
	private final double enemyMaximumYPosition;
	private int currentNumberOfEnemies;

	public final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final Stage stage;
	public final ImageView background;
	private final MediaPlayer backgroundMusic;
	
	private final ProjectileManager projectileManager;
	private final KeyHandler keyHandler;

	private final ActorManager actorManager;



	// Actor Variables
	public final List<ActiveActorDestructible> friendlyUnits;
	public final List<ActiveActorDestructible> enemyUnits;

	// Projectile Variables
	public final List<ActiveActorDestructible> userProjectiles;
	public final List<ActiveActorDestructible> enemyProjectiles;


	protected final LevelView levelView;
	private Controller controller;

	/**
	 * LevelParent constructor
	 * @param backgroundImageName background image file name
	 * @param backgroundMusicFile background music file name
	 * @param screenHeight game screen height
	 * @param screenWidth game screen width
	 * @param userSupplier supplier for user-controlled plane
	 * @param stage game's primary stage
	 */
	public LevelParent(String backgroundImageName, String backgroundMusicFile, double screenHeight, double screenWidth,
					   Supplier<UserPlane> userSupplier, Stage stage) {
		this.support = new PropertyChangeSupport(this);

		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();

		this.user = userSupplier.get();
		if (user == null) {
			throw new IllegalStateException("User is null!");
		}

		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		if (getClass().getResource(backgroundImageName) == null) {
			throw new IllegalArgumentException("Background image not found: " + backgroundImageName);
		}

		this.backgroundMusic = new MediaPlayer(new Media(getClass().getResource(backgroundMusicFile).toExternalForm()));
		this.backgroundMusic.setVolume(0.5);
		this.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
		if (getClass().getResource(backgroundMusicFile) == null) {
			throw new IllegalArgumentException("Background music not found: " + backgroundMusicFile);
		}

		this.stage = stage;

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		
		this.projectileManager = new ProjectileManager(root);
		this.keyHandler = new KeyHandler(this);

		this.actorManager = new ActorManager(root, friendlyUnits, enemyUnits);

		initializeTimeline();
		friendlyUnits.add(user);
	}


	// Level Initialisation Methods

	public abstract LevelView instantiateLevelView();

	/**
	 * Creates and returns the {@link LevelView} instance for the current level.
	 *
	 * @return The {@link LevelView} representing the level's visual elements
	 */
	public Scene initializeScene() {
		setUpBackground();
		setUpFriendlyUnits();
		setUpHeartDisplay();

		return scene;
	}

	/**
	 * sets up background
	 */
	private void setUpBackground() {
		initializeBackground();
	}

	/**
	 * adds background to root
	 */
	private void initializeBackground() {
		setUpBackgroundStuff();
		root.getChildren().add(background);
	}

	/**
	 * continues background setting up <br/>
	 * readies key handlers
	 */
	private void setUpBackgroundStuff() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		background.setOnKeyPressed(keyHandler::handleKeyPress);
		background.setOnKeyReleased(keyHandler::handleKeyRelease);
	}

	/**
	 * sets up user-controlled plane (and shield)
	 */
	private void setUpFriendlyUnits(){
		initialiseFriendlyUnits();
	}

	/**
	 * sets up health (heart) display
	 */
	private void setUpHeartDisplay(){
		levelView.showHeartDisplay();
	}

	/**
	 * starts up timeline used for game
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}



	// Actor Management

	public abstract void initialiseFriendlyUnits();

	/**
	 * adds user-controlled plane
	 * @param friendly user-controlled plane
	 */
	protected void addFriendlyUnit(ActiveActorDestructible friendly) {
		actorManager.addFriendlyUnits(friendly);
	}

	/**
	 * adds enemy plane(s)
	 * @param enemy enemy plane(s)
	 */
	protected void addEnemyUnits(ActiveActorDestructible enemy) {
		actorManager.addEnemyUnits(enemy);
	}

	public abstract void spawnEnemyUnits();



	// Projectile Management

	/**
	 * fires projectiles
	 */
	public void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		projectileManager.addProjectile(projectile, userProjectiles);
	}

	/**
	 * generates enemy projectiles
	 */
	private void generateEnemyFire() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemy instanceof FighterPlane fighterPlane) {
				ActiveActorDestructible projectile = fighterPlane.fireProjectile();
				if (projectile != null) {
					projectileManager.addProjectile(projectile, enemyProjectiles);
				}
			}
		}

	}



	// Collision & Penetration Handling

	/**
	 * handles all collisions between :
	 * <ul>
	 *     <li>user-controlled plane</li>
	 *     <li>enemy units</li>
	 *     <li>enemy projectiles</li>
	 * </ul>
	 */
	private void handleAllCollisions() {
		Collisions.handleCollisions(friendlyUnits, enemyUnits);
		Collisions.handleCollisions(userProjectiles, enemyUnits);
		Collisions.handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * handles situations where enemy units pass left bound of screen
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}



	// Start & End Game

	/**
	 * starts game
	 * <ul>
	 *     <li>starts timeline</li>
	 *     <li>starts background music</li>
	 * </ul>
	 */
	@Override
	public void startGame() {
		background.requestFocus();
		timeline.play();
		backgroundMusic.play();
	}

	/**
	 * stops game
	 * <ul>
	 *     <li>stops timeline</li>
	 *     <li>stops background music</li>
	 * </ul>
	 */
	@Override
	public void stopGame() {
		timeline.stop();
		backgroundMusic.stop();
	}

	/**
	 * ends game
	 * <ul>
	 *     <li>stops and clears timeline</li>
	 *     <li>resets keys</li>
	 *     <li>clears root</li>
	 *     <li>clears all on-screen units and projectiles</li>
	 *     <li>stops background music</li>
	 * </ul>
	 */
	@Override
	public void endGame(){
		timeline.stop();
		timeline.getKeyFrames().clear();

		background.setOnKeyPressed(null);
		background.setOnKeyReleased(null);

		root.getChildren().clear();

		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();

		backgroundMusic.stop();
	}



	// Game State Updates

	/**
	 * updates LevelView
	 */
	private void updateLevelView() {
		GameState.updateLevelView(levelView, user);
	}

	/**
	 * updates KillCount
	 */
	private void updateKillCount() {
		GameState.updateKillCount(user, currentNumberOfEnemies, enemyUnits);
	}

	/**
	 * Determines if an enemy has penetrated the game defenses based on its position
	 * relative to the screen width.
	 *
	 * @param enemy the enemy actor whose position is being evaluated
	 * @return true if the enemy has moved beyond the boundaries of the screen width, false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return GameState.enemyHasPenetratedDefenses(enemy, screenWidth);
	}

	/**
	 * updates Number of Enemies
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = GameState.updateNumberOfEnemies(enemyUnits);
	}

	public abstract void checkIfGameOver();

	/**
	 * Win Game <br/>
	 * <ul>
	 *     <li>stops timeline</li>
	 *     <li>shows Win Image</li>
	 * </ul>
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Lose Game <br/>
	 * <ul>
	 *     <li>stops timeline</li>
	 *     <li>shows Game Over Image</li>
	 *     <li>goes to Lose Screen</li>
	 * </ul>
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		LoseScreen();
	}

	/**
	 * Lose Screen display
	 */
	private void LoseScreen() {
        Lose lose = new Lose(getStage());
		lose.display();
	}



	// Level Transition

	/**
	 * Goes to Next Level after Ending Game
	 * @param levelName path to next level
	 */
	public void goToNextLevel(String levelName) {
		endGame();
		notifyLevelTransition(levelName);
	}

	/**
	 * notifying Level Transition-er of Level Transition
	 * @param levelName path to next level
	 */
	private void notifyLevelTransition(String levelName) {
		support.firePropertyChange("levelTransition", null, levelName);
	}



	// PropertyChangeListener Management

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	// Game Loop

	/**
	 * updates scene
	 */
	private void updateScene() {
		projectileManager.updateProjectiles(userProjectiles, enemyProjectiles);

		manageSpawn();
		manageActors();
		manageCollisions();
		manageUpdates();

		checkIfGameOver();
	}

	/**
	 * manages spawns
	 */
	public void manageSpawn() {
		spawnEnemyUnits();
		generateEnemyFire();
	}

	/**
	 * manages actors
	 */
	public void manageActors() {
		actorManager.updateActors();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		actorManager.removeDestroyedActors(userProjectiles);
	}

	/**
	 * manages collisions
	 */
	public void manageCollisions() {
		handleAllCollisions();
	}

	/**
	 * manages game updates
	 */
	public void manageUpdates() {
		updateKillCount();
		updateLevelView();
	}

	// Getters

	public UserPlane getUser() {
		return user;
	}

	public Group getRoot() {
		return root;
	}

	public Scene getScene() {
		return scene;
	}

	public Stage getStage() {
		return stage;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
}
