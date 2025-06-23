package game.entities;

import game.GameLib;
import game.util.Status;
import game.util.Vector2D;

public class Player extends GameObject {
    private int health;
    private int damage;
    private long shotCooldown;

    public Player(int health, Vector2D position, Vector2D velocity, long shotCooldown, double explosionEnd, double explosionStart, double radius) {
        super(position, velocity, radius);
        this.health = health;
        this.shotCooldown = shotCooldown;
        this.explosionEnd = explosionEnd;
        this.explosionStart = explosionStart;
        this.status = Status.ACTIVE;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getShotCooldown() {
        return shotCooldown;
    }

    public void setShotCooldown(long shotCooldown) {
        this.shotCooldown = shotCooldown;
    }

    @Override
    public void update(long delta) {
        double dx = getVelocity().getX() * delta;
        double dy = getVelocity().getY() * delta;

        double newX = getPosition().getX() + dx;
        double newY = getPosition().getY() + dy;

        //REVER ESSA PARTE DO CÓDIGO DE LIMITAÇÃO DA MOVIMENTAÇÃO DO PLAYER
        // Mantém o player dentro da tela
        if (newX < 0.0) newX = 0.0;
        if (newX >= GameLib.WIDTH) newX = GameLib.WIDTH - 1;

        if (newY < 0.0) newY = 0.0;
        if (newY >= GameLib.HEIGHT) newY = GameLib.HEIGHT - 1;

        getPosition().setX(newX);
        getPosition().setY(newY);
    }

    @Override
    public void render(long currentTime) {
        if (getStatus() == Status.EXPLODING) {
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getPosition().getX(), getPosition().getY(), alpha);
        } else if (getStatus() == Status.ACTIVE) {
            GameLib.drawPlayer(getPosition().getX(), getPosition().getY(), getRadius());
        }
    }
}
