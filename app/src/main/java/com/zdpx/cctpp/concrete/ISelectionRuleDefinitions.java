package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.rule.ISelectionRuleDefinition;

/**
 *
 */
public interface ISelectionRuleDefinitions {
    int Count();
    ISelectionRuleDefinition get(int index);
    ISelectionRuleDefinitions get(Guid guid);
}
