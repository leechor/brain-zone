package org.licho.brain.concrete;

/**
 *
 */
public class ForeignKeyStatePropertyObject extends BaseStatePropertyObject {
    public ForeignKeyStatePropertyObject(String name, boolean isPrivate) {
        super(name, true, isPrivate);
        		this.Value(-1.0);

    }
}
