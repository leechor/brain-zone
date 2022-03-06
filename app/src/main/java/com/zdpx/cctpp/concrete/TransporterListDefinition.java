package com.zdpx.cctpp.concrete;

/**
 *
 */
public class TransporterListDefinition extends ListDefinition {
    public static final TransporterListDefinition Instance = new TransporterListDefinition();

    public TransporterListDefinition() {
        super("TransporterList");
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new TransporterList(this, name);
    }

    @Override
    protected StringPropertyDefinition instance() {
        var tmp = new TransporterPropertyDefinition("Transporter");
        tmp.CanBeDeleted(false);
        return tmp;
    }
}
