package game.entities;

import game.GameLib;
import game.util.Status;
import game.util.Vector2D;

import java.awt.Color;

public class Player extends GameObject {

    private int health;
    private int damage;
    private long fireCooldown;
    private long nextFireCooldown = 0;

    // --- Construtor ---
    public Player(
            Color color,
            int health,
            Vector2D position,
            Vector2D velocity,
            double explosionEnd,
            double explosionStart,
            double radius,
            long fireCooldown
    ) {
        super(
                color,
                position,
                velocity,
                radius
        );

        this.health         = health;
        this.explosionEnd   = explosionEnd;
        this.explosionStart = explosionStart;
        this.status         = Status.ACTIVE;
        this.fireCooldown   = fireCooldown;
    }

    // --- Getters e Setters ---
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

    public long getFireCooldown() {
        return fireCooldown;
    }

    public void setFireCooldown(long fireCooldown) {
        this.fireCooldown = fireCooldown;
    }

    public long getNextFireCooldown() {
        return nextFireCooldown;
    }

    public void setNextFireCooldown(long nextFireCooldown) {
        this.nextFireCooldown = nextFireCooldown;
    }

    // --- Explosão ---
    public void explode(long currentTime) {
        setStatus(Status.EXPLODING);
        setExplosionStart(currentTime);
        setExplosionEnd(currentTime + 2000);
    }

    // --- Atualização do jogador ---
    @Override
    public void update(long delta) {
        double dx = getVelocity().getX() * delta;
        double dy = getVelocity().getY() * delta;

        double newX = getPosition().getX() + dx;
        double newY = getPosition().getY() + dy;

        // Limita movimentação do player dentro da tela
        if (newX < 0.0) newX = 0.0;
        if (newX >= GameLib.WIDTH) newX = GameLib.WIDTH - 1;

        if (newY < 0.0) newY = 0.0;
        if (newY >= GameLib.HEIGHT) newY = GameLib.HEIGHT - 1;

        getPosition().setX(newX);
        getPosition().setY(newY);
    }

    // --- Renderização do jogador ---
    @Override
    public void render(long currentTime) {
        if (getStatus() == Status.EXPLODING) {
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            GameLib.drawExplosion(getPosition().getX(), getPosition().getY(), alpha);
        }
        else if (getStatus() == Status.ACTIVE) {
            GameLib.setColor(getColor());
            GameLib.drawPlayer(getPosition().getX(), getPosition().getY(), getRadius());
        }
    }
}
