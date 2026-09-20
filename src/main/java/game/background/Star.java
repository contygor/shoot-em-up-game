package game.background;

import game.GameLib;

public class Star {

    // --- Atributos ---
    public double x;
    public double y;
    public double speed;

    // --- Construtor ---
    public Star(
            double x,
            double y,
            double speed
    ) {
        this.x     = x;
        this.y     = y;
        this.speed = speed;
    }

    // --- Atualização da posição ---
    public void update(double delta) {
        y += speed * delta;

        // Reinicia no topo da tela
        if (y > 720) {
            y = 0;
        }
    }

    // --- Renderização ---
    public void render() {
        GameLib.drawLine(x, y, x, y + 1);
    }
}
