package com.zdpx.cctpp.simuApi;

import cn.hutool.core.date.DateTime;

/**
 *
 */
public interface IDateTimeState extends IState {
    DateTime Value();

    DateTime NullableValue();
}
