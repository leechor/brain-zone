package com.zdpx.cctpp.enu;

/**
 *
 */
public enum Enum13 implements IEnumMask {
    ;

    Enum13() {
        this.mask = (1 << ordinal());
    }

    private final int mask;

    @Override
    public int mask() {
        return this.mask;
    }
}
