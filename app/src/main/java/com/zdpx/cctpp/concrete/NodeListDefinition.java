package com.zdpx.cctpp.concrete;

/**
 *
 */
public class NodeListDefinition extends ListDefinition {
	    public static final NodeListDefinition Instance =new NodeListDefinition();

    public NodeListDefinition() {
        super("NodeList");
    }

    @Override
    protected StringPropertyDefinition instance() {
        var tmp = new NodePropertyDefinition("Node");
        tmp.CanBeDeleted(false);
        return tmp;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new NodeList(this, name);
    }

}
