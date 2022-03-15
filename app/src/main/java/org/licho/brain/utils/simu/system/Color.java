package org.licho.brain.utils.simu.system;

/**
 *
 */
public class Color {
    public static Color Empty;
    public static Color Black;
    public static Color Gray;
    public byte R;
    public byte G;
    public byte B;

    public static Color Empty() {
        return null;
    }

    public static Color FromArgb(int r, int g, int b) {
        return null;
    }

    public Object ToArgb() {
        return null;
    }

    public boolean IsKnownColor() {
        return false;
    }

    public String Name() {
        return null;
    }

    public boolean IsEmpty() {
        return false;
    }
}
