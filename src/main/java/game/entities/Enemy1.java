package game.entities;

import game.GameLib;
import game.util.Vector2D;
import game.util.Status;

import java.awt.Color;

public class Enemy1 extends EnemyGeneric {

    private static long spawnCooldown = 1500;
    private static long nextSpawnTime = 0;

    // --- Getters e Setters estáticos para controle de spawn ---
    public static long getSpawnCooldown() {
        return spawnCooldown;
    }

    public static void setSpawnCooldown(long cooldown) {
        spawnCooldown = cooldown;
    }

    public static long getNextSpawnTime() {
        return nextSpawnTime;
    }

    public static void setNextSpawnTime(long time) {
        nextSpawnTime = time;
    }

    // --- Construtor ---
    public Enemy1(
            Color color,
            Vector2D position,
            Vector2D velocity,
            double radius,
            double angle,
            int health,
            int damage,
            long fireCooldown,
            double explosionStart,
            double explosionEnd,
            double dydx
    ) {
        super(
                color,
                position,
                velocity,
                radius,
                angle,
                health,
                damage,
                fireCooldown,
                explosionStart,
                explosionEnd,
                dydx
        );
    }

    // --- Atualização do inimigo ---
    @Override
    public void update(long delta) {
        double dx = getVelocity().getX();
        double dy = getVelocity().getY();
        double newX = getPosition().getX() + dx * delta + dy * getDydx() * delta;
        double newY = getPosition().getY() + dy * delta;

        getPosition().setX(newX);
        getPosition().setY(newY);

        if (newY > GameLib.HEIGHT) {
            setStatus(Status.INACTIVE);
        }
    }

    // --- Renderização do inimigo ---
    @Override
    public void render(long currentTime) {
        if (getStatus() == Status.ACTIVE) {
            GameLib.setColor(getColor());
            GameLib.drawCircle(getPosition().getX(), getPosition().getY(), getRadius());
        }
        else if (getStatus() == Status.EXPLODING) {
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());

            if (alpha < 1.0) {
                GameLib.drawExplosion(getPosition().getX(), getPosition().getY(), alpha);
            } else {
                setStatus(Status.INACTIVE);
            }
        }
    }
}
