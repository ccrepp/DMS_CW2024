package dev.ccr.dmscw2024.specials.shield;

import dev.ccr.dmscw2024.interfaces.Shield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Anakin extends ImageView implements Shield {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/AOTC/anakin.png";
    private static final int SHIELD_SIZE = 250;

    public Anakin(double xPosition, double yPosition) {

        try{
            this.setImage(new Image(getClass().getResource("/dev/ccr/dmscw2024/images/AOTC/anakin.png").toExternalForm()));
        } catch (Exception e){
            System.out.println("Anakin: I have failed you, Padme" + e.getMessage());
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
