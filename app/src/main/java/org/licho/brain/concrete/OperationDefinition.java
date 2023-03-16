package org.licho.brain.concrete;

/**
 *
 */
public class OperationDefinition extends AbstractGridObjectDefinition {
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
    public AbstractGridObjectDefinition DefaultDefinition() {
        return null;
    }
}
