package game.core;

import game.GameLib;
import game.background.StarField;
import game.entities.*;
import game.render.Renderer;
import game.util.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Player player;
    private final List<EnemyGeneric> enemies;
    private final List<ProjectileGeneric> projectiles;
    private final Renderer renderer;
    private final InputHandler inputHandler;

    private boolean running = true;

    public GameEngine() {
        GameLib.initGraphics();

        this.player = new Player(
                3,
                new Vector2D(GameLib.WIDTH / 2.0, GameLib.HEIGHT * 0.90),
                new Vector2D(0.0, 0.0),
                100,
                0,
                0,
                12.0
        );

        this.enemies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        StarField starField = new StarField(100);
        this.renderer = new Renderer(player, enemies, projectiles, starField);
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
        inputHandler.processInput(delta);
        player.update(delta);

        // Atualiza inimigos
        for (EnemyGeneric enemy : enemies) {
            enemy.update(delta);
        }

        // Atualiza projéteis
        for (ProjectileGeneric projectile : projectiles) {
            projectile.update(delta);
        }

        spawnEnemies(currentTime);
    }

    private void spawnEnemies(long currentTime) {
        // Enemy1
        if (currentTime >= Enemy1.getNextSpawnTime()) {
            Enemy1.setNextSpawnTime(currentTime + Enemy1.getSpawnCooldown());

            Enemy1 newEnemy1 = new Enemy1(
                    new Vector2D(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
                    new Vector2D(0.0, 0.20 + Math.random() * 0.15),
                    9.0,
                    3,
                    5,
                    500,                    // fireCooldown corrigido
                    0,
                    currentTime + 500,
                    0.0
            );

            enemies.add(newEnemy1);
        }

        // Enemy2
        if (currentTime >= Enemy2.getNextSpawnTime()) {
            Enemy2.setNextSpawnTime(currentTime + Enemy2.getSpawnCooldown());

            Enemy2 newEnemy2 = new Enemy2(
                    new Vector2D(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
                    new Vector2D(0.0, 0.25 + Math.random() * 0.20),
                    10.0,
                    5,
                    8,
                    1000,                   // fireCooldown corrigido
                    0,
                    currentTime + 1000,
                    0.05
            );

            enemies.add(newEnemy2);
        }


    }

    private void render(long currentTime) {
        renderer.renderAll(currentTime);
    }
}
