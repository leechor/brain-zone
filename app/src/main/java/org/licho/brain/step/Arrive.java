package org.licho.brain.step;

import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.enu.EntityType;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Arrive</b> step may be used to have an entity object notify its accepted visit requests that the entity has
 * 'arrived' to its current location.
 * <p>
 * Within the Standard Library, the Vehicle and Worker objects both use the <b>Arrive</b> step in their OnVisitingNode
 * process logic (logic related to be used as a movable resource for processing tasks).
 * <p>
 * The step’s current usage is for modeling a movable resource entity. It may be used by a resource entity to
 * explicitly notify a token waiting at a Seized or Move step that the entity resource has officially ‘arrived’ at the
 * requested destination node location and thus the token responsible for the resource move request may now be
 * released from the Seized or Move step to execute any subsequent process logic.
 */
@Setter
@Getter
public class Arrive extends Step {
    /**
     * The entity object that is notifying accepted visit requests of its arrival. May be specified as either the
     * object associated with the executing token, the parent object or as a specific object reference.
     */
    private EntityType entityType;
    /**
     * The specific entity object that is notifying accepted visit requests of its arrival.
     */
    private Entity entity;
}
