package org.licho.brain.concrete;

/**
 *
 */
public class ActivityDefinition extends AbstractGridObjectDefinition {
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
    public AbstractGridObjectDefinition DefaultDefinition() {
        return null;
    }
}
