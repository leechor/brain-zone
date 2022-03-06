package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.DataFormat;

/**
 *
 */
@PropertyDefinitionName("ForeignKeyProperty")
public class ForeignKeyPropertyDefinition extends StringPropertyDefinition {
    public ForeignKeyPropertyDefinition(String name) {
        super(name);
        this.dataFormat = DataFormat.List;
    }
}
