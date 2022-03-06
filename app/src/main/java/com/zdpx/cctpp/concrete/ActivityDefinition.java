package com.zdpx.cctpp.concrete;

/**
 *
 */
public class ActivityDefinition extends AbsDefinition {
    private ActivityDefinition() {
        super("Activity");
    }

    @Override
    public Class<?> ElementType() {
        return null;
    }

    @Override
    public Class<?> RunSpaceType() {
        return null;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return null;
    }
}
