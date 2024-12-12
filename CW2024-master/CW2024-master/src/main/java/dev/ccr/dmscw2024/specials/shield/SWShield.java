package dev.ccr.dmscw2024.specials.shield;

import dev.ccr.dmscw2024.interfaces.Shield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SWShield extends ImageView implements Shield {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/ISDShield.png";
    private static final int SHIELD_SIZE = 350;

    public SWShield(double xPosition, double yPosition) {

        try{
            System.out.println("ShieldImage: Loading ISDShield");
            this.setImage(new Image(getClass().getResource("/dev/ccr/dmscw2024/images/SW/ISDShield.png").toExternalForm()));
            System.out.println("ShieldImage: ISDShield LOADED");
        } catch (Exception e){
            System.out.println("ShieldImage: ISDShield Loading FAILED" + e.getMessage());
            throw e;
        }

        setFitHeight(SHIELD_SIZE);
        setFitWidth(SHIELD_SIZE);
        setPosition(xPosition, yPosition);
        setVisible(false);
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
