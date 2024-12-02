package dev.ccr.dmscw2024.specials.shield;

public interface Shield {
    void showShield();
    void hideShield();
    void setPosition(double x, double y);

    double getFitWidth();
    double getFitHeight();
}