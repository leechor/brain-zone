package org.licho.brain.api;

/**
 *
 */
@FunctionalInterface
public interface CalendarEventHandler {
    void apply(Object eventObject);
}
