package dev.ccr.dmscw2024;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ISDShield extends ImageView {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/ISDShield.png";
    private static final int SHIELD_SIZE = 350;

    public ISDShield(double xPosition, double yPosition) {

        try{
            System.out.println("ShieldImage: Loading ISDShield");
            this.setImage(new Image(getClass().getResource("/dev/ccr/dmscw2024/images/ISDShield.png").toExternalForm()));
            System.out.println("ShieldImage: ISDShield LOADED");
        } catch (Exception e){
            System.out.println("ShieldImage: ISDShield Loading FAILED" + e.getMessage());
            throw e;
        }

        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        //this.setImage(new Image(IMAGE_NAME));

        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    public void showShield() {
        this.setVisible(true);
    }

    public void hideShield() {
        this.setVisible(false);
    }

}
