package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

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
        Schema schema = ((TableStatesDefinition) this.myElementInstance.assignerDefinition).getSchema();
        Table parent = schema.Parent();
        return parent.getDisplayName(this.ParentObjectRunSpace, this);
    }
}
