package dev.ccr.dmscw2024.specials.shield;

import dev.ccr.dmscw2024.interfaces.Shield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView implements Shield {
	
	private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/shield.png";
	private static final int SHIELD_SIZE = 200;
	
	public ShieldImage(double xPosition, double yPosition) {

		try{
			System.out.println("ShieldImage: Loading ShieldImage");
			this.setImage(new Image(getClass().getResource("/dev/ccr/dmscw2024/images/shield.png").toExternalForm()));
			System.out.println("ShieldImage: ShieldImage LOADED");
		} catch (Exception e){
			System.out.println("ShieldImage: ShieldImage Loading FAILED" + e.getMessage());
			throw e;
		}

		setFitHeight(SHIELD_SIZE);
		setFitWidth(SHIELD_SIZE);
		setPosition(xPosition, yPosition);
		setVisible(false);

		/*
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		//this.setImage(new Image(IMAGE_NAME));

		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);

		 */
	}

	@Override
	public void showShield() {
		this.setVisible(true);
	}
	
	@Override
	public void hideShield() {
		this.setVisible(false);
	}

	@Override
	public void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
	}

}
