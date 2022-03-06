package com.zdpx.cctpp.event;

import com.zdpx.cctpp.concrete.IGridObject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class EventHandler<TEventArgs> {
    private List<IEvent<TEventArgs>> eventDeleteArray = new CopyOnWriteArrayList<>();

    public void subscribe(IEvent<TEventArgs> method) {
        this.eventDeleteArray.add(method);
    }

    public void unSubscribe(IEvent<TEventArgs> method) {
        this.eventDeleteArray.remove(method);
    }

    public List<IEvent<TEventArgs>> getEvent() {
        return this.eventDeleteArray;
    }

    public void subscribe(EventHandler<TEventArgs> value) {
        this.eventDeleteArray.addAll(value.getEvent());
    }

    public void unSubscribe(EventHandler<TEventArgs> value) {
        this.eventDeleteArray.removeAll(value.getEvent());
    }

    public void fire(Object source, TEventArgs eventArgs) {
        eventDeleteArray.forEach(t -> t.invoke(source, eventArgs));
    }

    public void close() {
        if (!eventDeleteArray.isEmpty()) {
            eventDeleteArray.clear();
        }
    }

    public static <T> void subscribe(EventHandler<T> target, IEvent<T> value) {
        if (target == null) {
            target = new EventHandler<>();
        }
        target.subscribe(value);
    }

    public static <T> void unSubscribe(EventHandler<T> target, IEvent<T> value) {
        if (target != null) {
            target.unSubscribe(value);
        }
    }

    public static <T> void subscribe(EventHandler<T> target, EventHandler<T> value) {
        if (value == null) {
            return;
        }
        if (target == null) {
            target = new EventHandler<>();
        }
        target.eventDeleteArray.addAll(value.getEvent());
    }

    public static <T> void unSubscribe(EventHandler<T> target, EventHandler<T> value) {
        if (value == null || target == null) {
            return;
        }
        target.eventDeleteArray.removeAll(value.getEvent());
    }


    public static <T> void fire(EventHandler<T> event, Object target, T args) {
        if (event != null) {
            event.fire(target, args);
        }
    }
}
