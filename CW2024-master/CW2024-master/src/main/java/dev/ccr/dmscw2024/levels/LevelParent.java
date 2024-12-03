package dev.ccr.dmscw2024.levels;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.*;
import java.util.stream.Collectors;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.projectile.ProjectileManager;
import dev.ccr.dmscw2024.utility.*;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

import java.util.function.Supplier;

public abstract class LevelParent implements Level {
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
	public final ImageView background;
	
	private final ProjectileManager projectileManager;
	private final KeyHandler keyHandler;

//	private final ActorManager actorManager;

//	private final GameLoopManager gameLoopManager;


	// Actor Variables
	public final List<ActiveActorDestructible> friendlyUnits;
	public final List<ActiveActorDestructible> enemyUnits;

	// Projectile Variables
	public final List<ActiveActorDestructible> userProjectiles;
	public final List<ActiveActorDestructible> enemyProjectiles;


	protected final LevelView levelView;
	//protected final LevelInitialiser levelInitialiser;


	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, Supplier<UserPlane> userSupplier) {
		this.support = new PropertyChangeSupport(this);

		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();

		this.user = userSupplier.get();

//		this.levelInitialiser = levelInitialiser != null
//				? levelInitialiser
//				: createDefaultLevelInitialiser(backgroundImageName, screenHeight, screenWidth);

		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		
		this.projectileManager = new ProjectileManager(root);
		this.keyHandler = new KeyHandler(this);

//		this.actorManager = new ActorManager(root);

//		this.gameLoopManager = new GameLoopManager(this);


//		initializeScene();
		initializeTimeline();
		friendlyUnits.add(user);
	}

//	private LevelInitialiser createDefaultLevelInitialiser(String backgroundImageName, double screenHeight, double screenWidth) {
//		return new LevelInitialiser(backgroundImageName, screenHeight, screenWidth, root, instantiateLevelView(), new KeyHandler(this)) {
//			@Override
//			protected void initialiseFriendlyUnits(Group root, UserPlane user) {
//
//			}
//
//			@Override
//			protected void spawnEnemyUnits(Group root) {
//
//			}
//		};
//	}


	// Level Initialisation Methods

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
//		levelInitialiser.initialiseScene(levelInitialiser.getRoot(), user);
//
//		actorManager.addFriendlyUnit(user);
//		root.getChildren().add(user);

		setUpBackground();
		setUpFriendlyUnits();
		setUpHeartDisplay();

		return scene;
	}

	private void setUpBackground() {
		initializeBackground();
	}

	private void initializeBackground() {
		setUpBackgroundStuff();
		root.getChildren().add(background);
	}

	private void setUpBackgroundStuff() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		background.setOnKeyPressed(keyHandler::handleKeyPress);
		background.setOnKeyReleased(keyHandler::handleKeyRelease);
	}

	private void setUpFriendlyUnits(){
		initialiseFriendlyUnits();
	}

	private void setUpHeartDisplay(){
		levelView.showHeartDisplay();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}



	// Actor Management

//	public ActorManager getActorManager() {
//		return actorManager;
//	}

	protected abstract void initialiseFriendlyUnits();

	protected void addEnemyUnits(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected abstract void spawnEnemyUnits();

	private void updateActors() {
		List<List<ActiveActorDestructible>> allActors
				= List.of(
				friendlyUnits,
				enemyUnits,
				userProjectiles,
				enemyProjectiles);
		allActors.forEach(list->list.forEach(ActiveActorDestructible::updateActor));
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed()).collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}



	// Projectile Management

	public void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		projectileManager.addProjectile(projectile, userProjectiles);
	}

	private void generateEnemyFire() {
		for (ActiveActorDestructible enemy:enemyUnits) {
			if (enemy instanceof FighterPlane fighterPlane) {
				ActiveActorDestructible projectile = fighterPlane.fireProjectile();
				projectileManager.addProjectile(projectile, enemyProjectiles);
			}
		}

	}



	// Collision & Penetration Handling

	private void handleAllCollisions() {
		Collisions.handleCollisions(friendlyUnits, enemyUnits);
		Collisions.handleCollisions(userProjectiles, enemyUnits);
		Collisions.handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}



	// Start & End Game

	@Override
	public void startGame() {
		background.requestFocus();
		timeline.play();
//		gameLoopManager.startGame();
	}

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

//		gameLoopManager.endGame();
	}



	// Game State Updates

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	public abstract void checkIfGameOver();

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}



	// Level Transition

	public void goToNextLevel(String levelName) {
		System.out.println("GOING TO : " + levelName);
		endGame();
		notifyLevelTransition(levelName);
	}

	private void notifyLevelTransition(String levelName) {
		System.out.println("Notifying Observers...");
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

	private void updateScene() {
		projectileManager.updateProjectiles(userProjectiles, enemyProjectiles);

		manageSpawn();
		manageActors();
		manageCollisions();
		manageUpdates();

		checkIfGameOver();
	}

	public void manageSpawn() {
		spawnEnemyUnits();
		generateEnemyFire();
	}

	public void manageActors() {
		updateActors();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		removeAllDestroyedActors();
	}

	public void manageCollisions() {
		handleAllCollisions();
	}

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

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	public List<ActiveActorDestructible> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	protected double getScreenHeight() {
		return screenHeight;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
}
