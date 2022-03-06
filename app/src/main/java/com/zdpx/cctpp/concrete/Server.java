package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.Entity;

/**
 * a <b>Server</b> represents a capacity resource with optional constrained input and output buffers.
 * <p>
 * A <b>Server</b> is an object that contains three capacity station with associated queues. The three stations that
 * make up a standard Server is the InputBuffer, Processing and OutputBuffer stations, When a Server is used in a model.
 * it appears as though an entity enters the Server, is processed and then leaves the Server, A more detailed
 * explanation
 * of what happens to the entity is that when it first enters the Server, it enters the InputBuffer station, if the
 * InputBuffer property is greater than 0, It waits here util there is an available capacity in the Processing station,
 * The capacity of this station is controlled by the <I>Initial Capacity</I> property of the Server,
 * Once it has been processed, it moves into the OutputBuffer station, if the <I>OutputBuffer</I> is set greater than 0,
 * and it waits until it can leave the Server. The three station approach allows for the capability to have both
 * infinite
 * and finite capacity queues.
 * <p>
 * The logic that is being performed once the entity enters the Server object spawns what is called
 * a {@link com.zdpx.cctpp.concrete.Token}, A Token is a completely different object
 * than an {@link Entity}.
 * <p>
 * Within the Processing station of the Server, the <I>Process Type</I> may be a 'Specific Time' or based on a
 * 'Task Sequence'.The default 'Specific Time' will delay the entity at the processing station of the Server for the
 * simple
 * <I>Processing Time</I> specified. The 'Task Sequence' option will require a number of <I>Processing Task</I> and
 * associated information to be specified.
 * <p></p>
 * A Server may have failures.
 * <p></p>
 * State assignments can be made on entering, before processing, after processing, before exiting, on balking and on
 * reneging the Server, Secondary Resources may be seized and/or released by the entity for processing
 * or upon entering the Server, and before and after processing occurs, the <I>Add-On Process Triggers</I> allow
 * additional logic to be specified when using the Server.
 * <p></p>
 * An entity processing at a Server may be interrupted with the <I>Interrupt</I> step. THe Processing Time delay is the
 * only interruptable delay within the Server.
 * <p></p>
 * The <I>Seize Constraint Logic</I> repeat group supports imposing additional constraints on an entity's request to
 * seize the object by referencing <I>Constraint Logic</I> elements(only if the InputBuffer is > 0).
 * <p></p>
 * The <I>Immediately Try Seize and Immediately Try Allocate When Released</I> options within the Other Processing
 * Options (and Secondary Resources area) section of properties are typically advanced users...
 */
public class Server {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
							FixedDefinition fixedDefinition) {
        FixedDefinition definition = new FixedDefinition("Server", activeModel, fixedDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Server.init(definition);
    }

    public static void init(FixedDefinition fixedDefinition) {

    }
}
