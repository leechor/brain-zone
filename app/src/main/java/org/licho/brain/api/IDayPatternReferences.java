package org.licho.brain.api;

/**
 *
 */
public interface IDayPatternReferences extends ISimioCollection<IDayPattern> {
    int Count();

    void setCount(int count);

    IDayPattern getDayPattern();

    void setDayPattern(IDayPattern dayPattern);
}
