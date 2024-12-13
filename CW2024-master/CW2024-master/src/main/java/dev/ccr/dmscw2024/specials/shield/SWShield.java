package dev.ccr.dmscw2024.specials.shield;

import dev.ccr.dmscw2024.interfaces.Shield;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * SW Shield - Star Wars shield's properties and behaviour
 */
public class SWShield extends ImageView implements Shield {

    private static final String IMAGE_NAME = "/dev/ccr/dmscw2024/images/SW/ISDShield.png";
    private static final int SHIELD_SIZE = 350;

    /**
     * SWShield constructor
     * @param xPosition initial x-axis position
     * @param yPosition initial y-axis position
     */
    public SWShield(double xPosition, double yPosition) {
        try{
            this.setImage(new Image(getClass().getResource("/dev/ccr/dmscw2024/images/SW/ISDShield.png").toExternalForm()));
        } catch (Exception e){
            System.out.println("ShieldImage: ISDShield Loading FAILED" + e.getMessage());
            throw e;
        }

        setFitHeight(SHIELD_SIZE);
        setFitWidth(SHIELD_SIZE);
        setPosition(xPosition, yPosition);
        setVisible(false);
    }

    /**
     * shows shield by enabling visibility
     */
    @Override
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * hides shield by disabling visibility
     */
    @Override
    public void hideShield() {
        this.setVisible(false);
    }

    /**
     * sets position of shield accordingly
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    @Override
    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }
}
