package org.licho.brain.concrete;

import org.licho.brain.simuApi.extensions.ISelectionRule;

/**
 *
 */
public class SelectionRuleInfo extends AbsPropertyObject {
    public SelectionRuleInfo(SelectionRuleDefinition selectionRuleDefinition, String name) {
        super(selectionRuleDefinition, name);
    }

    public ISelectionRule CreateRule() {
        return ((SelectionRuleDefinition)this.objectDefinition).CreateRule(this.properties);
    }

    @Override
    public boolean NotifyParentOfErrors() {
        return false;
    }

}
