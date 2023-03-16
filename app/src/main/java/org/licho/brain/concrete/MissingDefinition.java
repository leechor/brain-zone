package org.licho.brain.concrete;

/**
 *
 */
public class MissingDefinition extends AbstractGridObjectDefinition {
    public static MissingDefinition Instance = new MissingDefinition();

    public MissingDefinition() {
        super("Missing");
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

    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }

}
