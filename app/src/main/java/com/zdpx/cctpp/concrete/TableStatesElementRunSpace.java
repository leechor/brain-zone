package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class TableStatesElementRunSpace extends AbsBaseRunSpace {
    public TableStatesElementRunSpace(IntelligentObjectRunSpace parent,
                                      MayApplication application, TableStates intelligentObject) {
        super(parent, application, intelligentObject);
    }

    @Override
    public String HierarchicalDisplayName() {
        Schema schema = ((TableStatesDefinition) this.myElementInstance.objectDefinition).getSchema();
        Table parent = schema.Parent();
        return parent.getDisplayName(this.ParentObjectRunSpace, this);
    }
}
