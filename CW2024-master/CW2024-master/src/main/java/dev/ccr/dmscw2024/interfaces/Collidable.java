package dev.ccr.dmscw2024.interfaces;

public interface Collidable {
    boolean checkCollision(Collidable other);
    void onCollision(Collidable other);
}
