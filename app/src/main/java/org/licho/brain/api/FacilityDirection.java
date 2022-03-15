package org.licho.brain.api;

/**
 *
 */
public class FacilityDirection {
    public final double dx;
    public final double dy;
    public final double dz;

    public static final FacilityDirection ZERO = new FacilityDirection(0.0, 0.0, 0.0);

    public FacilityDirection(double dx, double dy, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public double computeLength() {
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public FacilityLocation toUnitLength() {
        // TODO: 2021/11/2
        return null;
    }

}
