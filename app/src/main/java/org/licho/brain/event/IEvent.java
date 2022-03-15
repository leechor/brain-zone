package org.licho.brain.event;

@FunctionalInterface
public interface IEvent<TEventArgs extends Object> {
    void invoke(Object source, TEventArgs eventArgs);
}


