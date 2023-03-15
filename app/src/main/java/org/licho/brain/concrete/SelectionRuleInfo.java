package org.licho.brain.concrete;

import org.licho.brain.api.extensions.ISelectionRule;

/**
 *
 */
public class SelectionRuleInfo extends AbsPropertyObject {
    public SelectionRuleInfo(SelectionRuleDefinition selectionRuleDefinition, String name) {
        super(selectionRuleDefinition, name);
    }

    public ISelectionRule CreateRule() {
        return ((SelectionRuleDefinition)this.assignerDefinition).CreateRule(this.properties);
    }

    @Override
    public boolean NotifyParentOfErrors() {
        return false;
    }

}
