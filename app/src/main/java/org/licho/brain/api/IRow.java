package org.licho.brain.api;

import cn.hutool.core.date.DateTime;

/**
 *
 */
public interface IRow {
    IProperties getProperties();

    DateTime getTimeIndexStartTime();

    DateTime getTimeIndexEndTime();
}
