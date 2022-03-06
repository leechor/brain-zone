package com.zdpx.cctpp.concrete;

/**
 *
 */
public class OperationDefinition extends AbsDefinition{
    public OperationDefinition(String name) {
        super(name);
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
