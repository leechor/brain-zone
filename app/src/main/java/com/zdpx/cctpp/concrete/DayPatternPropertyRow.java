package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public class DayPatternPropertyRow extends IntelligentObjectProperty {
    private DayPattern dayPattern;


    public DayPatternPropertyRow(DayPatternPropertyDefinition dayPatternPropertyDefinition, Properties properties) {
        super(dayPatternPropertyDefinition, properties);

    }

    public String StringValue() {
        if (this.dayPattern != null) {
            return this.dayPattern.Name();
        }
        return super.StringValue();
    }
}
