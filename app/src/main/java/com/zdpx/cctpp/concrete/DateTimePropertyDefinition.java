package com.zdpx.cctpp.concrete;

import cn.hutool.core.date.DateTime;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.NumericDataType;

/**
 *
 */
public class DateTimePropertyDefinition extends NumericDataPropertyDefinition {
    private static final String defaultValue = new DateTime("2008-01-01", "yyyy-MM-dd").toString();
    private final DateTime dateTime;
    private boolean bool_9;

    public DateTimePropertyDefinition(String name) {
        super(name, NumericDataType.None);
        this.defaultString = DateTimePropertyDefinition.defaultValue;
        this.dataFormat = DataFormat.DateTime;
        this.dateTime = DateTime.now();
        super.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        this.bool_9 = true;
    }

    public void method_61(boolean param0) {
        this.bool_9 = param0;
    }

}
