package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseStepProperty;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class EntryPointPropertyRow extends IntelligentObjectProperty {
    public List<ExitPointPropertyRow> ExitPointPropertyRows;
    public AbsBaseStepProperty AbsBaseStepProperty;

    public EntryPointPropertyRow(EntryPointPropertyDefinition entryPointPropertyDefinition, Properties properties) {
        super(entryPointPropertyDefinition, properties);
        this.ExitPointPropertyRows = new ArrayList<ExitPointPropertyRow>(1);
        this.AbsBaseStepProperty = (AbsBaseStepProperty) properties.AbsPropertyObject;
    }
}
