package org.licho.brain.brainApi;

/**
 *
 */
@FunctionalInterface
public interface CalendarEventHandler {
    void apply(Object eventObject);
}
