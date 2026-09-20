package game.render;

import game.background.StarField;
import game.entities.EnemyGeneric;
import game.entities.Player;
import game.entities.ProjectileGeneric;
import game.GameLib;

import java.awt.*;
import java.util.List;

public class Renderer {

    // --- Atributos ---
    private final Player player;
    private final List<EnemyGeneric> enemies;
    private final List<ProjectileGeneric> projectilesPlayer;
    private final List<ProjectileGeneric> projectilesEnemy;
    private final StarField starField;

    // --- Construtor ---
    public Renderer(
            Player player,
            List<EnemyGeneric> enemies,
            List<ProjectileGeneric> projectilesPlayer,
            List<ProjectileGeneric> projectilesEnemy,
            StarField starField
    ) {
        this.player            = player;
        this.enemies           = enemies;
        this.projectilesPlayer = projectilesPlayer;
        this.projectilesEnemy  = projectilesEnemy;
        this.starField         = starField;
    }

    // --- Renderização de todos os elementos ---
    public void renderAll(long currentTime) {
        starField.render();

        // Player
        if (player != null) {
            player.render(currentTime);
        }

        // Inimigos
        for (EnemyGeneric enemy : enemies) {
            enemy.render(currentTime);
        }

        // Tiros do player
        for (ProjectileGeneric projectile : projectilesPlayer) {
            projectile.render(currentTime);
        }

        // Tiros dos inimigos
        for (ProjectileGeneric projectile : projectilesEnemy) {
            projectile.render(currentTime);
        }
    }
}
