package com.zdpx.cctpp.concrete;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Queues, or lines of entities waiting to be processed, can be found in many places . This
 * provides tremendous flexibility in allowing the user to queue entities up at different points throughout the model
 * . It also creates ease in modeling blocking. When placed in the Facility Window, the standard library objects all
 * have their Input Buffer queue, their Processing queue and their Output Buffer queue automatically animated.
 */
@Setter
@Getter
public class BaseQueue<E> extends LinkedTransferQueue<E> {

    /**
     * Only visible for queues that are attached to an object, such as a Server, Worker, Vehicle or Node. The object
     * to which the queue is attached.
     */
    private IntelligentObject attachedTo;

    /**
     * Name of the queue state holding entities that are displayed by this object.
     */
    private String queueState;

    /**
     * An expression that is evaluated per object in the queue to determine if that object is visible or not. To
     * reference an object in the queue for which the expression is being evaluated, use the "Candidate" keyword.
     * For example, the expression "Candidate.MyEntity.MyState > 1" assumes all objects in the queue will always be
     * of type 'MyEntity' and will only show those that have a 'MyState' value greater than 1.
     */
    private Expression visibility;

    /**
     * An expression indicating the direction an object should be oriented alont the X axis.
     */
    private Expression directionX;

    /**
     * An expression indicating the direction an object should be oriented alont the Y axis.
     */
    private Expression directionY;

    /**
     * An expression indicating the direction an object should be oriented alont the Z axis.
     */
    private Expression directionZ;

    /**
     * An expression indicating the degrees of clockwise roll from upright an object should be oriented.
     */
    private Expression roll;

}
