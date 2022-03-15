package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

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
