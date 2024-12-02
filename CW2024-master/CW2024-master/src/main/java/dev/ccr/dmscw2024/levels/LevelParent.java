package dev.ccr.dmscw2024.levels;

import java.util.*;
import java.util.stream.Collectors;

import dev.ccr.dmscw2024.fundamentals.ActiveActorDestructible;
import dev.ccr.dmscw2024.planes.FighterPlane;
import dev.ccr.dmscw2024.planes.PlaneFactory;
import dev.ccr.dmscw2024.planes.user.UserPlane;
import dev.ccr.dmscw2024.planes.user.XWing;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

import java.util.function.Supplier;

public abstract class LevelParent extends Observable implements Level {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private LevelView levelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, Supplier<UserPlane> userSupplier) {
		System.out.println("LevelParent: Constructor START");
		this.root = new Group();
		System.out.println("LevelParent: Root CREATED");
		this.scene = new Scene(root, screenWidth, screenHeight);
		System.out.println("LevelParent: Scene CREATED");
		this.timeline = new Timeline();
		System.out.println("LevelParent: Timeline CREATED");

		this.user = userSupplier.get();
		System.out.println("LevelParent: UserPlane CREATED - " + user.getClass().getSimpleName());

		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		System.out.println("LevelParent: Background Image CREATED");
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;

		initializeTimeline();
		friendlyUnits.add(user);
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		setUpBackground();
		setUpFriendlyUnits();
		setUpHeartDisplay();

		System.out.println("Scene Graph Contents POST-INITIALIZATION: ");
		getRoot().getChildren().forEach(node -> {
			System.out.println(node.getClass().getSimpleName() + " : " + node);
		});
		return scene;
	}

	private void setUpBackground() {
		initializeBackground();
	}

	private void setUpFriendlyUnits(){
		initializeFriendlyUnits();
	}

	private void setUpHeartDisplay(){
		levelView.showHeartDisplay();
	}

	@Override
	public void startGame() {
		background.requestFocus();
		timeline.play();
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
	}

	public void goToNextLevel(String levelName) {
		System.out.println("GOING TO : " + levelName);
		endGame();
		notifyLevelTransition(levelName);
	}

	private void notifyLevelTransition(String levelName) {
		System.out.println("Notifying Observers...");
		setChanged();
		notifyObservers(levelName);
	}

	private void updateScene() {
		manageSpawn();
		manageActors();
		manageCollisions();
		manageUpdates();

		checkIfGameOver();
	}

	private void manageSpawn() {
		spawnEnemyUnits();
		generateEnemyFire();
	}

	private void manageActors() {
		updateActors();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		removeAllDestroyedActors();
	}

	private void manageCollisions() {
		handleAllCollisions();
	}

	private void manageUpdates() {
		updateKillCount();
		updateLevelView();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		setUpBackgroundImage();
		setUpKeyHandlers();
		root.getChildren().add(background);
	}

	private void setUpBackgroundImage() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
	}

	private void setUpKeyHandlers() {
		background.setOnKeyPressed(this::handleKeyPress);
		background.setOnKeyReleased(this::handleKeyRelease);
	}

	private void handleKeyPress(KeyEvent e) {
		KeyCode kc = e.getCode();
		if (kc == KeyCode.UP || kc == KeyCode.W) user.moveUp();
		if (kc == KeyCode.DOWN || kc == KeyCode.S) user.moveDown();
		if (kc == KeyCode.LEFT || kc == KeyCode.A) user.moveLeft();
		if (kc == KeyCode.RIGHT || kc == KeyCode.D) user.moveRight();
		if (kc == KeyCode.SPACE) fireProjectile();
	}

	private void handleKeyRelease(KeyEvent e) {
		KeyCode kc = e.getCode();
		if (kc == KeyCode.UP || kc == KeyCode.W || kc == KeyCode.DOWN || kc == KeyCode.S) user.stopVertically();
		if (kc == KeyCode.LEFT || kc == KeyCode.A || kc == KeyCode.RIGHT || kc == KeyCode.D) user.stopHorizontal();
	}

	private void fireProjectile() {
		addProjectile(user.fireProjectile(),userProjectiles);
	}

	public void addProjectile(ActiveActorDestructible projectile, List<ActiveActorDestructible> projectileList) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			projectileList.add(projectile);
		}
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

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

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	public List<ActiveActorDestructible> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
}
