package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public class TableStateDefinition extends AbsDefinition{

    private Schema schema;

    public TableStateDefinition(String name) {
        super(name);
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }


    @Override
    public AbsPropertyObject CreateInstance(String name) {
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
    public AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
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
