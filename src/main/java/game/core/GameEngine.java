package game.core;

import game.GameLib;
import game.background.StarField;
import game.entities.*;
import game.render.Renderer;
import game.util.Status;
import game.util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Player player;
    private final List<EnemyGeneric> enemies;
    private final List<ProjectileGeneric> projectilesPlayer;
    private final List<ProjectileGeneric> projectilesEnemy;
    private final Renderer renderer;
    private final InputHandler inputHandler;
    private boolean running = true;

    public GameEngine() {
        GameLib.initGraphics();

        this.player = new Player(
                Color.BLUE,
                3,
                new Vector2D(GameLib.WIDTH / 2.0, GameLib.HEIGHT * 0.90),
                new Vector2D(0.0, 0.0),
                0,
                0,
                12,
                200
        );

        this.enemies = new ArrayList<>();
        this.projectilesPlayer = new ArrayList<>();
        this.projectilesEnemy = new ArrayList<>();

        StarField starField = new StarField(100);
        this.renderer = new Renderer(player, enemies, projectilesPlayer, projectilesEnemy, starField);
        this.inputHandler = new InputHandler(player);
    }

    public void run() {
        long delta;
        long currentTime;
        long previousTime = System.currentTimeMillis();

        while (running) {
            currentTime = System.currentTimeMillis();
            delta = currentTime - previousTime;
            previousTime = currentTime;

            update(delta, currentTime);
            render(currentTime);
            GameLib.display();

            if (inputHandler.isEscapePressed()) running = false;
        }

        System.exit(0);
    }

    private void update(long delta, long currentTime) {
        if (player.getStatus() == Status.ACTIVE) {
            inputHandler.processInput(delta);
        } else {
            player.setVelocity(new Vector2D(0.0, 0.0));
        }

        player.update(delta);

        if (player.isActive() &&
                inputHandler.isShooting() &&
                currentTime > player.getNextFireCooldown()) {

            double shotX = player.getPosition().getX();
            double shotY = player.getPosition().getY() - 2 * player.getRadius();

            ProjectilePlayer shot = new ProjectilePlayer(
                    Color.GREEN,
                    new Vector2D(shotX, shotY),
                    new Vector2D(0.0, -0.45),
                    2.0,
                    1
            );

            projectilesPlayer.add(shot);
            player.setNextFireCooldown(currentTime + player.getFireCooldown());
        }

        for (EnemyGeneric enemy : enemies) {
            enemy.update(delta);

            if (enemy instanceof Enemy1 && enemy.isActive()) {
                enemy.tryToShoot(currentTime, player.getPosition().getY())
                        .ifPresent(projectile -> {
                            projectile.setVelocity(new Vector2D(0.0, 0.45));
                            projectilesEnemy.add(projectile);
                        });
            }
        }

        for (ProjectileGeneric p : projectilesPlayer) p.update(delta);
        for (ProjectileGeneric p : projectilesEnemy) p.update(delta);

        checkPlayerHit(currentTime);
        checkCollisions(currentTime);

        enemies.removeIf(enemy -> enemy.getStatus() == Status.EXPLODING && currentTime > enemy.getExplosionEnd());

        if (player.getStatus() == Status.EXPLODING && currentTime > player.getExplosionEnd()) {
            player.setStatus(Status.ACTIVE);
        }

        spawnEnemies(currentTime);
    }

    private void checkPlayerHit(long currentTime) {
        for (ProjectileGeneric proj : projectilesEnemy) {
            if (!player.isActive()) continue;

            double dx = proj.getPosition().getX() - player.getPosition().getX();
            double dy = proj.getPosition().getY() - player.getPosition().getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (proj.getRadius() + player.getRadius()) * 0.8) {
                player.setStatus(Status.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
                break;
            }
        }

        for (EnemyGeneric enemy : enemies) {
            if (!player.isActive() || !enemy.isActive()) continue;

            double dx = enemy.getPosition().getX() - player.getPosition().getX();
            double dy = enemy.getPosition().getY() - player.getPosition().getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (enemy.getRadius() + player.getRadius()) * 0.8) {
                player.setStatus(Status.EXPLODING);
                player.setExplosionStart(currentTime);
                player.setExplosionEnd(currentTime + 2000);
                break;
            }
        }
    }

    private void checkCollisions(long currentTime) {
        List<ProjectileGeneric> toRemove = new ArrayList<>();

        for (ProjectileGeneric projectile : projectilesPlayer) {
            for (EnemyGeneric enemy : enemies) {
                if (!enemy.isActive()) continue;

                double dx = enemy.getPosition().getX() - projectile.getPosition().getX();
                double dy = enemy.getPosition().getY() - projectile.getPosition().getY();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < (enemy.getRadius() + projectile.getRadius())) {
                    enemy.setHealth(enemy.getHealth() - projectile.getDamage());

                    if (enemy.getHealth() <= 0) {
                        enemy.setStatus(Status.EXPLODING);
                        enemy.setExplosionStart(currentTime);
                        enemy.setExplosionEnd(currentTime + 500);
                    }

                    toRemove.add(projectile);
                    break;
                }
            }
        }

        projectilesPlayer.removeAll(toRemove);
    }

    private void spawnEnemies(long currentTime) {
        if (currentTime >= Enemy1.getNextSpawnTime()) {
            Enemy1.setNextSpawnTime(currentTime + Enemy1.getSpawnCooldown());

            Enemy1 newEnemy1 = new Enemy1(
                    Color.CYAN,
                    new Vector2D(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
                    new Vector2D(0.0, 0.20 + Math.random() * 0.15),
                    9.0,
                    0.0,
                    1,
                    5,
                    500,
                    0,
                    currentTime + 500,
                    0.0
            );

            enemies.add(newEnemy1);
        }

        if (currentTime >= Enemy2.getNextSpawnTime()) {
            Enemy2.setNextSpawnTime(currentTime + Enemy2.getSpawnCooldown());

            Enemy2 newEnemy2 = new Enemy2(
                    Color.MAGENTA,
                    new Vector2D(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
                    new Vector2D(0.0, 0.25 + Math.random() * 0.20),
                    10.0,
                    0.0,
                    1,
                    8,
                    1000,
                    0,
                    currentTime + 500,
                    0.05
            );

            enemies.add(newEnemy2);
        }
    }

    private void render(long currentTime) {
        renderer.renderAll(currentTime);
    }
}
