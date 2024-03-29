package org.licho.brain.concrete.entity;

import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.element.Network;
import org.licho.brain.enu.EntityTravelMode;
import org.licho.brain.brainEnums.ElementScope;
import lombok.Data;

import java.util.function.Function;

/**
 * <b>ModelEntity</b> automatically created within a new Project Library. The ModelEntity is the default entity
 * definition in your project.
 * @unused
 */
public class ModelEntity extends Entity {

    /**
     * Rate Table Instance,	The initial desired speed value for objects of this type.
     */
    private double initialDesiredSpeed;

    private EntityTravelMode initialTravelMode;

    /**
     * Network Element Property, The network of links used by objects of this type to travel between node locations.
     * This includes the default 'Global' network and 'No Network (FreeSpace)'.
     */
    private Network initialNetwork;

    /**
     * Exit & Re-enter, Rotate in Place, Reverse.
     * When traveling on a network, the default method used by objects of
     * this type to turn around at a node in order to move in the opposite direction on a bidirectional link. The
     * 'Turnaround Method' property of a Network element may also be used to override this default on a
     * network-by-network basis.
     */
    private Function networkTurnaroundMethod;

    public ModelEntity(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }


    @Data
    static class Size {
        /**
         * The initial graphical length of the entity.
         */
        private double length;

        /**
         * The initial graphical width of the entity.
         */
        private double width;

        /**
         * The initial graphical height of the entity.
         */
        private double height;
    }

}
