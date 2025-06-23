package game.util;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D scale(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        return (mag == 0.0) ? new Vector2D(0.0, 0.0) : new Vector2D(x / mag, y / mag);
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }
}
