package org.licho.brain.step;

import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.enu.EntityType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <b>SetNetwork</b> step may be used to set the network of links used by an entity to travel between node locations.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SetNetwork extends Step {
    /**
     * The new network value to set. 'No Network (Free Space)' is the default.
     */
    private String newNetworkName = "No Network (Free Space)";

    /**
     * The entity object whose network value is to be set. May be specified as the object associated with the
     * executing token, the parent object, or as a specific object reference.
     */
    private EntityType entityType;

    /**
     * The specific entity object whose network value is to be set.
     */
    private Entity entity;

}
