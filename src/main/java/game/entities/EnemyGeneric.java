package game.entities;

import game.util.Vector2D;
import game.util.Status;

import java.awt.*;
import java.util.Optional;

public abstract class EnemyGeneric extends GameObject {

  private double dydx;
  private int health;
  private int damage;
  private double angle;
  private long fireCooldown;
  private long nextShotTime;

  // --- Construtor ---
  public EnemyGeneric(
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
            radius
    );

    this.health = health;
    this.damage = damage;
    this.angle = angle;
    this.fireCooldown = fireCooldown;
    this.dydx = dydx;
    this.setExplosionStart(explosionStart);
    this.setExplosionEnd(explosionEnd);
    this.setStatus(Status.ACTIVE);
    this.nextShotTime = 0;
  }

  // --- Getters e Setters ---
  public double getDydx() {
    return dydx;
  }

  public void setDydx(double dydx) {
    this.dydx = dydx;
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

  public double getAngle() {
    return angle;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public long getFireCooldown() {
    return fireCooldown;
  }

  public void setFireCooldown(long fireCooldown) {
    this.fireCooldown = fireCooldown;
  }

  public long getNextShotTime() {
    return nextShotTime;
  }

  public void setNextShotTime(long nextShotTime) {
    this.nextShotTime = nextShotTime;
  }

  // --- Lógica de disparo do inimigo ---
  public Optional<ProjectileEnemy> tryToShoot(long currentTime, double playerY) {
    if (currentTime > getNextShotTime() && getPosition().getY() < playerY) {
      double vx = Math.cos(getAngle()) * 0.45;
      double vy = Math.sin(getAngle()) * -0.45;
      Vector2D velocity = new Vector2D(vx, vy);

      ProjectileEnemy projectile = new ProjectileEnemy(
              Color.RED,
              new Vector2D(getPosition().getX(), getPosition().getY()),
              velocity,
              2.0,
              getDamage()
      );

      setNextShotTime(currentTime + (long)(200 + Math.random() * 500));
      return Optional.of(projectile);
    }

    return Optional.empty();
  }

  // --- Métodos abstratos obrigatórios ---
  @Override
  public abstract void update(long delta);

  @Override
  public abstract void render(long currentTime);
}
