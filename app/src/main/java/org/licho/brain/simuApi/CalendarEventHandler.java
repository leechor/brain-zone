package org.licho.brain.simuApi;

/**
 *
 */
@FunctionalInterface
public interface CalendarEventHandler {
    void apply(Object eventObject);
}
