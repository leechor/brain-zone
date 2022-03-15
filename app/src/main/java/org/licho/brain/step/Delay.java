package org.licho.brain.step;

import org.licho.brain.concrete.Expression;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The <b>Delay</b> step delays the arriving token in the step for the specified time duration.
 * <p>
 * The <i>Delay Multiplier</i> property allows for dynamic adjustments to the <i>Delay Time</i>. If the process that is
 * executing the Delay step is suspended, then the base delay time remaining is saved. When the process is resumed,
 * the <i>Delay Multiplier</i> expression property is re-evaluated and the base delay time remaining multiplied by that
 * number to schedule the end of the delay back on the simulation event calendar.
 * <p>
 * For example, suppose the <i>Delay Time</i> is specified as '1' hour and the <i>Delay Multiplier</i> expression
 * returns the value of '2' (based on model entity state or table value). This results in a delay time of 2 hours. If
 * the process is suspended after 0.5 hours have elapsed and the delay thus 25% completed, hen the base delay time
 * remaining is 0.75 hours. When the process is resumed, if the <i>Delay Multiplier</i> expression property now
 * returns the value ‘1.5’ then the end of the delay is scheduled back on the calendar to occur at TimeNow + (0.75*1
 * .5 =) 1.125 hours. If the <i>Delay Multiplier expression</i> property returns a negative value, then a runtime
 * error is thrown.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Delay extends Step {

    /**
     * The duration of the delay. This property may be specified with any expression, including using a random sample
     * from a distribution. A special case is to use the keyword 'Math.Epsilon', which causes the token or entity to
     * be delayed to the end of the current time advance -- still executing at the same simulated time but after all
     * normal events have been processed.
     */
    private Expression delayTime;

    /**
     * Increases or decreases the delay time by some factor. Specify 2, for example, to mean that the delay is twice
     * as long. Note that this expression is automatically reevaluated if the process is suspended and resumed,
     * potentially adjusting the remaining delay time.
     */
    private double delayMultiplier;

    /**
     * Indicates whether token delays at this step can be interrupted using the Interrupt step.
     */
    private boolean interruptable;

}
