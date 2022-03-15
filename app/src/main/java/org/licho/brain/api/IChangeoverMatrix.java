package org.licho.brain.api;

import org.licho.brain.api.enu.TimeUnit;

/**
 *
 */
public interface IChangeoverMatrix {
    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    TimeUnit getDurationTimeUnit();

    void setDurationTimeUnit(TimeUnit durationUnit);

    INamedList getStringList();

    void setStringList(INamedList namedList);

    void setValue(String from, String to, double value);

    void setValue(int from, int to, double value);

    double getValue(String from, String to);

    double getValue(int from, int to);
}
