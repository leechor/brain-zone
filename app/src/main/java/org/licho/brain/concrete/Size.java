package org.licho.brain.concrete;

import java.util.Objects;

/**
 *
 */
public class Size {

    private double length;
    private double width;
    private double height;

    public Size() {

    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Size(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return Double.compare(size.length, length) == 0 && Double.compare(size.width, width) == 0 && Double.compare(size.height, height) == 0;
    }

    public static Size valueOf(String value) {
        Size result = new Size();
        String[] array = value.split(" ");
        if (array.length == 3) {
            result.length = Double.parseDouble(array[0]);
            result.width = Double.parseDouble(array[1]);
            result.height = Double.parseDouble(array[2]);
        }
        return result;
    }


    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }

    @Override
    public String toString() {
        return "Size{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }


    public static final Size Instance = new Size(0.0, 0.0, 0.0);
}
