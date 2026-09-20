package game.entities;

import game.util.Vector2D;
import game.util.Status;

import java.awt.Color;

public abstract class GameObject {

    protected Color color;
    protected Vector2D position;
    protected Vector2D velocity;
    protected double radius;
    protected Status status;
    protected double explosionStart;
    protected double explosionEnd;

    // --- Construtor ---
    public GameObject(
            Color color,
            Vector2D position,
            Vector2D velocity,
            double radius
    ) {
        this.color    = color;
        this.position = position;
        this.velocity = velocity;
        this.radius   = radius;
        this.status   = Status.ACTIVE;
    }

    // --- Getters e Setters ---
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getExplosionStart() {
        return explosionStart;
    }

    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    public double getExplosionEnd() {
        return explosionEnd;
    }

    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    // --- Utilitários ---
    public boolean isExploding(double currentTime) {
        return status == Status.EXPLODING && currentTime < explosionEnd;
    }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }

    // --- Métodos abstratos ---
    public abstract void update(long delta);
    public abstract void render(long currentTime);
}
