package game.entities;

import game.GameLib;
import game.util.Vector2D;
import game.util.Status;

import java.awt.Color;

public class ProjectilePlayer extends ProjectileGeneric {

    // --- Construtor ---
    public ProjectilePlayer(
            Color color,
            Vector2D position,
            Vector2D velocity,
            double radius,
            int damage
    ) {
        super(
                color,
                position,
                velocity,
                radius,
                damage
        );
    }

    // --- Atualização da posição do projétil ---
    @Override
    public void update(long delta) {
        Vector2D newPosition = getPosition().add(getVelocity().scale(delta));
        setPosition(newPosition);
    }

    // --- Renderização do projétil ---
    @Override
    public void render(long currentTime) {
        if (getStatus() == Status.ACTIVE) {
            GameLib.setColor(getColor());
            GameLib.drawCircle(getPosition().getX(), getPosition().getY(), getRadius());
        }
    }
}
