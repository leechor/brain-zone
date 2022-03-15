package org.licho.brain.concrete;

import org.licho.brain.rule.ISelectionRuleDefinition;

/**
 *
 */
public interface ISelectionRuleDefinitions {
    int Count();
    ISelectionRuleDefinition get(int index);
    ISelectionRuleDefinitions get(Guid guid);
}
