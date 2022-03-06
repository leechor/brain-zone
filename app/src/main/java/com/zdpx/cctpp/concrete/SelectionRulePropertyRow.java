package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.utils.simu.IListeners;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class SelectionRulePropertyRow extends IntelligentObjectProperty implements IListeners {
    private SelectionRuleInfo selectionRuleInfo;

    public SelectionRulePropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    public Iterable<IListener> Listeners() {
        if (this.selectionRuleInfo != null && this.selectionRuleInfo.properties != null) {
            return this.selectionRuleInfo.properties.AllPropertyInstances().stream().map(IListener.class::cast).collect(Collectors.toList());
        }
        return List.of();
    }
}
