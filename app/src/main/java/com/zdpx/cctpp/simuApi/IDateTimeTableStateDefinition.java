package com.zdpx.cctpp.simuApi;

import cn.hutool.core.date.DateTime;

/**
 *
 */
public interface IDateTimeTableStateDefinition {
    DateTime getInitialValue();

    void setInitialValue(DateTime value);
}
