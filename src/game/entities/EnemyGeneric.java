package game.entities;

import game.util.Vector2D;
import game.util.Status;

public abstract class EnemyGeneric extends GameObject {

  private double dydx;
  private int health;
  private int damage;
  private long fireCooldown;

  public EnemyGeneric(Vector2D position, Vector2D velocity, double radius, int health, int damage, long fireCooldown, double explosionStart, double explosionEnd, double dydx
  ) {
    super(position, velocity, radius);
    this.health = health;
    this.damage = damage;
    this.fireCooldown = fireCooldown;
    this.dydx = dydx;
    this.setExplosionStart(explosionStart);
    this.setExplosionEnd(explosionEnd);
    this.setStatus(Status.ACTIVE);
  }

  // Getters e Setters
  public double getDydx() { return dydx; }
  public void setDydx(double dydx) { this.dydx = dydx; }

  public int getHealth() { return health; }
  public void setHealth(int health) { this.health = health; }

  public int getDamage() { return damage; }
  public void setDamage(int damage) { this.damage = damage; }

  public long getFireCooldown() { return fireCooldown; }
  public void setFireCooldown(long fireCooldown) { this.fireCooldown = fireCooldown; }

  @Override
  public abstract void update(long delta);

  @Override
  public abstract void render(long currentTime);
}
