package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 500;
	private static final int SHIELD_Y_POSITION = 500;
	private final ShieldImage shieldImage;

	private final Group root;
	private final Boss boss;


	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.boss = new Boss();
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	private void addImagesToRoot() {
		root.getChildren().addAll(boss.getShieldImage());
	}

	public void showShield() {
		if (boss != null) {
			boss.getShieldImage().showShield();
		}
	}

	public void hideShield() {
		if (boss != null) {
			boss.getShieldImage().hideShield();
		}

	}
}
