package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.Entity;

/**
 * A <b>Combiner</b> object may be used to model a process that groups multiple entities together and then attaches the
 * batch members to a parent entity.
 * <p>
 * The Combiner object contains <em>four capacity stations</em> with associated queues:
 * <ul>
 *     <li>one station for the parent input buffer</li>
 *     <li>one for the member input buffer</li>
 *     <li>the processing station</li>
 *     <li>the output buffer station</li>
 * </ul>
 * The four station approach
 * allows for the capability to have both infinite and finite capacity queues.
 * <p>
 * <h3>Batching Details</h3>
 * An {@link Entity} arrives at the parent input node of the <b>Combiner</b>, this station has a queue and capacity.
 * Entities also arrive to the member input node which has its own queue and capacity. The entities wait in these queues
 * until they batch together according to the logic specified in the properties of the Combiner. The
 * <i>Batch Quantity</i> properties can be specified by an expression(i.e. a variable or a random distribution) as
 * well as a string value. The <i>Matching Rule</i> properties specifies if the entities should meet a certain
 * criteria before they can be batched together. If there is a criteria, the <i>Member Match Expression</i> and the
 * <i>Parent Match Expression</i> is where the criteria are specified, For the <i>Matching rule</i> of 'Match
 * Members' and 'Match Members and Parent', the match will occur on the first match encountered by any waiting
 * entities, not just when the first waiting entity has a match. Therefore, entities are nto necessarily matched
 * based on their order of arrival. Refer to the <i>BatchLogic</i> element ofr additional details on batching specifics.
 * <p>
 * <h3>Reneging a Parent Entity From Parent Input Buffer vs. Using a Releasing Batch Early Trigger</h3>
 * If a reneged trigger is used to a parent entity from a combiner's parent input buffer, then the parent entity
 * immediately cancels its outstanding request. There is no attempt to collect any further batch members. The parent
 * entity is then physically removed from the buffer and either destroyed or sent to some specified node in the model.
 * <p>
 * A release batch early trigger, on the other hand, causes the parent entity to attempt to collect as many batch
 * members
 * as it can up to its target batch quantity(ies). The parent entity then continues normal processing at the combiner
 * (i.e.
 *  existing the parent input buffer and entering the combiner's processing station).
 * <h3>Transferring In and Processing at the Combiner</h3>
 * The transfer-in times for the parent and member entities run concurrently. For example, if a <i>Parent Transfer-In
 * Time</i>
 * is '1' minute and a <i>Member Transfer-In Time</i> is '2' minutes, they will be ready to match(given matching
 * requirements)
 * after 2 minutes if they arrive at the Combiner at the same time.
 * <p>
 * THe Combiner does not only batch entities together, it also has process logic and capacity logic similar to a
 * {@link Server}
 * Object. If the entity be processed by the Combiner, it then appears at the process station's queue. After the logic
 * of the process has completed, if there is space the entity will be moved to the output member queue.
 * <p>
 *  Within the Processing station of the Combiner, the <i>Process Type</i> may be a 'Specific Time' or based on a
 *  'Task Sequence'.
 *  The default 'Specific Time' will delay the entity at the processing station of the Combiner for the simple
 *  <i>Processing Time</i> specified. The 'Task Sequence' option will require a number of <i>Processing Tasks</i> and
 *  associated information to be specified.
 * <h3>Additional Information</h3>
 * A Combiner may have failures...
 */
public class Combiner {
	public static void init(InternalReference internalReference, ActiveModel activeModel,
							FixedDefinition fixedDefinition) {
		FixedDefinition definition = new FixedDefinition("Combiner", activeModel, fixedDefinition);
		internalReference.updateSameIntelligentObjectDefinition(definition);
		Combiner.init(definition);
	}

	public static void init(FixedDefinition fixedDefinition) {
	}
}