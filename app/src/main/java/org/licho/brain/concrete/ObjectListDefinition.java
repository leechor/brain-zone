package org.licho.brain.concrete;

/**
 *
 */
public class ObjectListDefinition extends ListDefinition {
    public static final ObjectListDefinition ObjectListDefinition0 = new ObjectListDefinition();

    public ObjectListDefinition() {
        super("ObjectList");
    }


    @Override
    protected StringPropertyDefinition instance() {
        var tmp = new ObjectInstancePropertyDefinition("Object");
        tmp.CanBeDeleted(false);
        tmp.RequiredValue(true);
        return tmp;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new ObjectList(this, name);
    }

}
