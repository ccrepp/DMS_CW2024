package dev.ccr.dmscw2024.levels;

public interface Collidable {
    boolean checkCollision(Collidable other);
    void onCollision(Collidable other);
}
