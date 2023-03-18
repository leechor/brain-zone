package org.licho.brain.element;

import org.licho.brain.concrete.Event;
import lombok.Getter;
import lombok.Setter;

/**
 * The Timer element fires a stream of events according to a specified Interval Type.
 * if Interval Type is 'Time' or 'TimeInState', then the first event occurs at Time Offset
 * is RateTale, 'EventCount' or 'ArrivalTable', then the first event time is determined by the rate pattern.
 */
@Setter
@Getter
public class Timer extends Element {

    /**
     * determine the time interval between two successive timer events.
     */
    private TimeType type;
    /**
     * the time offset until the first timer event
     */
    private int offset;

    /**
     * the time interval between two successive timer events
     */
    private int interval;

    /**
     * the name of the state variable that will determine which time periods are included in calculating the timer's
     * elapsed time.
     */
    private String stateName;

    /**
     * determine which time periods are included in calculating the timer's elapsed time. Only time periods that the
     * state variable has this value will be included.
     */
    private Object stateValue;

    /**
     * defines how the rate of timer events changes over time, the interArrival times following a non-stationary
     * poisson process.
     */
    private Object rateTable;
    /**
     * scale the rate values specified in the rate table.
     */
    private double rateScale;
    /**
     * the name of the event which determines when the next timer event is fired
     */
    private Event triggeringEventName;
    /**
     * the number of triggering event occurrences required to trigger the next timer event.
     */
    private int triggeringEventCount;
    /**
     * the name of the numeric table property(table column) that contains the discrete timers when timer events are
     * expected to be fired. the number of timer events to be fired at each scheduled time is determined by the
     * <b>Arrival Events Per Time Slot</b> property. When the timer's event is fired, a reference to the table row
     * holding the arrival time will be automatically assigned to any process tokens created by the event.
     */
    private String arrivalTimeProperty;
    /**
     * the number of arrival events expected to occur at each of the discrete timer defined in the <i>Arrival Time</i>
     * property. to vary the number of arrival events for each time slot, add a numeric column that contains those values
     * to the referenced arrival table, then specify the name of that table column here.
     */
    private int arrivalEventsPerTimerSlot;
    /**
     * Indicates whether the arrival pattern in the table should be repeated when the end of the pattern is reached.
     */
    private boolean repeatArrivalPattern;
    /**
     * The maximum number of times after initialization or reset that the time can fire its event
     */
    private int maximumEvents;
    /**
     * The maximum time allowed after initialization or reset that the timer can fire events.
     */
    private int maximumTime;
    /**
     * Expression used to model differences (typically random) between the scheduled times in the arrival table and
     * the actual times during the simulation run that the arrival events occurs.
     * The specified expression may return a negative or positive value. A negative value indicates that the actual
     * arrival time will occur earlier than the scheduled time by that duration. A positive value indicates that the
     * actual arrival time will occur later than the scheduled time by that duration.
     * To vary the arrival time deviation for each time slot, add an expression column that contains those expressions
     * to the referenced arrival table. Then specify the name of that table column here.
     * <p/> <b>NOTE</b>: This feature is automatically disabled if random sampling in the simulation run is disabled.
     */
    private int arrivalTimeDeviation;
    /**
     * The probability that a scheduled arrival in the arrival pattern will be a no-show and thus the timer's event
     * not actually fired. Enter the chance of a no-show as a value between 0.0 (0%) and 1.0 (100%).
     * To vary the no-show probability for each time slot, add a numeric column that contains those probabilities
     * to the referenced arrival table. Then specify the name of that table column here.
     * <p/><b>NOTE</b>: This feature is automatically disabled if random sampling in the simulation run is disabled.
     */
    private int arrivalNoShowProbability;
    /**
     * The random number stream used to generate <i>interArrival time</i> (if rate table) or make probabilistic
     * no-show decisions (if arrival table).
     */
    private Object randomNumberStream;
    /**
     * The name of the event whose occurrence will trigger a reset of the timer.
     */
    private String resetEventName;
    /**
     * Indicates whether the timer is enabled when the system is initialized.
     * </p><b>NOTE</b>: Refer to the user-assignable 'Enabled' state variable of a timer to dynamically enable
     * or disable it during a simulation run.
     */
    private boolean initiallyEnabled;
    /**
     * Specifies if statistics are to be automatically reported for this element.
     */
    private boolean reportStatistics;
    /**
     * indicates whether the timer is currently enabled. Assignable values are True(1) or False(0)
     */
    private int enabled;
    /**
     * the time(or time in state if applicable) that has elapsed since the last timer event or reset.
     */
    private int elapsedTime;
    /**
     * the timer event
     */
    private Event event;
    /**
     * resets the timer
     */
    private Event reset;
}
