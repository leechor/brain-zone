package org.licho.brain.brainApi;

/**
 *
 */
public class FacilityLocation {
    public final double X;
    public final double Y;
    public final double Z;

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ() {
        return Z;
    }


    public static final FacilityLocation Zero = new FacilityLocation(0.0, 0.0, 0.0);

    public  FacilityLocation(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }



}
