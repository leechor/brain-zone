package com.zdpx.cctpp.concrete;

import java.util.Objects;

/**
 *
 */
public class Location {
    public Double x;

    public static boolean parse(String s, Location location) {
        location = Location.DefaultLocation;
        String[] array = s.split(",");
        if (array.length == 3) {
            try {
                location.x = Double.parseDouble(array[0]);
                location.y = Double.parseDouble(array[1]);
                location.z = Double.parseDouble(array[2]);
            } catch (NullPointerException | IllegalArgumentException e) {
                return false;
            }
        }
        return true;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double y;
    public Double z;

    public static final Location DefaultLocation = new Location(0.0, 0.0, 0.0);
    public static final Location EmptyLocation = new Location(null, null, null);

    public Location() {
        this.x = 0D;
        this.y = 0D;
        this.z = 0D;
    }
    public Location(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Direction sub(Location location) {
        return new Direction(this.x - location.y, this.y - location.y, this.z - location.z);
    }

    public Location add(Location l2) {
        return new Location(this.x + l2.x, this.y + l2.y, this.z + l2.z);
    }

    public Location divide(double d1) {
        return new Location(this.x / d1, this.y / d1, this.z / d1);
    }


    public Location add(Direction l2) {
        return new Location(this.x + l2.x, this.y + l2.y, this.z + l2.z);
    }

    public Location sub(Direction l2) {
        return new Location(this.x - l2.x, this.y - l2.y, this.z - l2.z);
    }

    public boolean equal(Location l2) {
        return Objects.equals(this.x, l2.x) && Objects.equals(this.y, l2.y) && Objects.equals(this.z, l2.z);
    }


    public String getString() {
        return "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Double.compare(location.z,
                z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


}
