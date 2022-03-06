package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.NumericDataType;

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
