package org.licho.brain.concrete;

import java.util.Objects;

/**
 *
 */
public class Direction {
    public static Direction Instance = new Direction(0.0, 0.0, 0.0);
    public double x;
    public double y;
    public double z;

    public Direction(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Double.compare(direction.x, x) == 0 && Double.compare(direction.y, y) == 0 && Double.compare(direction.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public static final Direction direction = new Direction(0.0, 0.0, 0.0);

    public Direction sub(Direction target) {
        return new Direction(-target.x, -target.y, -target.z);
    }

    public Direction divide(double target) {
        return new Direction(this.x / target, this.y / target, this.z / target);
    }

    public Direction multiple(double target) {
        return new Direction(this.x * target, this.y * target, this.z * target);
    }

    public Direction add(Direction target) {
        return new Direction(this.x + target.x, this.y + target.y, this.z + target.z);
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double distance() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
}
