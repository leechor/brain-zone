package org.licho.brain.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@Data
@RequiredArgsConstructor
public class FacilitySize {

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double length;
    public double width;
    public double height;

    public FacilitySize(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
}
