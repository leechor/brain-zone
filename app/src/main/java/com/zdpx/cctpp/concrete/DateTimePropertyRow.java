package com.zdpx.cctpp.concrete;


import com.zdpx.cctpp.utils.simu.system.DateTime;

/**
 *
 */
public class DateTimePropertyRow extends NumericDataPropertyRow {
    private DateTime dateTime;

    public DateTimePropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    public DateTime TimeValue() {
        if (this.dateTime == DateTime.MinValue) {
            return DateTime.TryParse(super.getObjectName()) == null ? DateTime.MinValue :
                    DateTime.TryParse(super.getObjectName());
        }
        return this.dateTime;
    }

    @Override
    public Object GetNativeObject() {
        return this.TimeValue();
    }


    public void TimeValue(DateTime value) {
        this.dateTime = value;
    }

    public DateTime method_81(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                              IntelligentObjectRunSpace objectRunSpace, int param3) {
// TODO: 2021/12/9
        return new DateTime();
    }
}
