package org.licho.brain.concrete;

/**
 *
 */
public class TableStatesDefinition extends AbstractGridObjectDefinition {
    private Schema schema;

    public TableStatesDefinition(Schema schema) {
        super("TableStatesDefinition" + TableStatesDefinition.smethod_6());
        this.schema = schema;
    }

    private static String smethod_6() {
        return null;
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

    public Schema getSchema() {
        return this.schema;
    }
}
