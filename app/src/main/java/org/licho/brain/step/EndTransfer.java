package org.licho.brain.step;

import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.enu.EntityType;
import lombok.Data;

/**
 * The EndTransfer step may be used to indicate that the parent object, object associated with the executing token,
 * or specific entity object reference has completed transfer into an object or station.
 */
@Data
public class EndTransfer {

    /**
     * The entity object that is ending the transfer. Specified as either the object associated with the executing
     * token, the parent object, or as a specific object reference.
     */
    private EntityType entityType;

    /**
     * The specific entity object whose transfer is to be ended.
     */
    private Entity entity;
}
