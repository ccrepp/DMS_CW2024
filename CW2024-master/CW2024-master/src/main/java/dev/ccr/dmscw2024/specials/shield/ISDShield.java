package dev.ccr.dmscw2024.specials.shield;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ISDShield extends ImageView implements Shield{

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

        setFitHeight(SHIELD_SIZE);
        setFitWidth(SHIELD_SIZE);
        setPosition(xPosition, yPosition);
        setVisible(false);

//        this.setLayoutX(xPosition);
//        this.setLayoutY(yPosition);
//        //this.setImage(new Image(IMAGE_NAME));
//
//        this.setVisible(false);
//        this.setFitHeight(SHIELD_SIZE);
//        this.setFitWidth(SHIELD_SIZE);
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
