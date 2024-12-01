package dev.ccr.dmscw2024.fundamentals;

public interface Collidable {
    boolean checkCollision(Collidable other);
    void onCollision(Collidable other);
}
