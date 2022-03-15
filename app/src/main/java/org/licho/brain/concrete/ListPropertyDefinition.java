package org.licho.brain.concrete;

import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.NumericDataType;

@PropertyDefinitionName("ListProperty")
public class ListPropertyDefinition extends NumericDataPropertyDefinition {
    public String[] displays;
    private AbsListProperty absListProperty;

    public ListPropertyDefinition(String name, AbsListProperty absListProperty) {
        super(name, NumericDataType.None);
        this.defaultString = super.NullNullString();
        this.dataFormat = DataFormat.List;
        this.absListProperty = absListProperty;
    }
}
