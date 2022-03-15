package org.licho.brain.brainApi.extensions;

/**
 * An interface that is implemented by a user-code object to give functionality to an instance of an element.
 */
public interface IElement {
    /**
     * Called when the simulation is initializing. When this is called the user-code object can do things like
     * schedule initial events on the calendar.
     */
    void Initialize();

    /**
     * Called when the simulation is shutting down.
     */
    void Shutdown();
}
