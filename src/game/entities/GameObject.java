package game.entities;

import game.util.Vector2D;
import game.util.Status;

public abstract class GameObject
{
    protected Vector2D position;
    protected Vector2D velocity;
    protected double radius;
    protected Status status;
    protected double explosionStart;
    protected double explosionEnd;

    public GameObject(Vector2D position, Vector2D velocity, double radius)
    {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.status = Status.ACTIVE;
    }

    public Vector2D getPosition()
    {
        return position;
    }

    public Vector2D getVelocity()
    {
        return velocity;
    }

    public void setPosition(Vector2D position)
    {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity)
    {
        this.velocity = velocity;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public double getExplosionStart()
    {
        return explosionStart;
    }

    public double getExplosionEnd()
    {
        return explosionEnd;
    }

    public void setExplosionStart(double explosionStart)
    {
        this.explosionStart = explosionStart;
    }

    public void setExplosionEnd(double explosionEnd)
    {
        this.explosionEnd = explosionEnd;
    }

    public boolean isExploding(double currentTime)
    {
        return status == Status.EXPLODING && currentTime < explosionEnd;
    }

    public boolean isActive()
    {
        return status == Status.ACTIVE;
    }

    public abstract void update(long delta);
    public abstract void render(long currentTime);
}
