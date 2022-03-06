package com.zdpx.cctpp.step;

import com.zdpx.cctpp.concrete.IntelligentObject;
import com.zdpx.cctpp.concrete.Resource;
import com.zdpx.cctpp.concrete.ResourceType;
import com.zdpx.cctpp.concrete.node.Node;
import com.zdpx.cctpp.enu.OwnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * The Move step may be used to request a move from one or more movable resources that have been seized by a
 * specified owner. The executing token is held at the Move step until all of requested movements are completed.
 * <br>
 * A list of objects for moving may be specified by using the Definitions / Lists and referencing the 'ListName'.
 * Alternatively, a list of objects may be specified within a table column and referencing the 'TableName.ColumnName'
 * as the Resource List Name property.
 * <br>
 * This step may be used in conjunction with the Seized step and the Release step.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Move extends Step {

    /**
     * The resources that will be requested to move.
     */
    private Resource resourceMoveRequest;

    @Data
    public static class ResourceMoveRequests {
        /**
         * The method for specifying the resource object(s) that will be requested to move.
         */
        private ResourceType type;

        /*
         * The name of the object list from which one or more resource objects will be requested to move.
         */
        private List<String> resourceNameList;

        /**
         * The name of the specific node location that the resource(s) will be requested to move to. If not
         * specified, and the owner object requesting the movement is an entity object at a node, then this property
         * defaults to that owner entity's current node.
         */
        private Node destination;

        /**
         * The number of resource objects that will be requested to move.
         */
        private Long numberOfResources;

        /**
         * The priority of the move request if a PlanVisit or SelectVisit step is used by a resource to choose a
         * visit destination from multiple candidates. If not specified, and the owner object requesting the movement
         * is an entity object, then this property defaults to that entity's Priority state value.
         */
        private Long movePriority;

        /**
         * The order in which to request moves from resources that have been seized by the owner object.
         */
        private String order;

        /**
         * Optional condition evaluated for each seized resource that must evaluate to true for the object to be
         * moved. In the expression, use the syntax Candidate.[ObjectClass].[Attribute] to reference an attribute of
         * the seized resource objects (e.g. Candidate.Object.Capacity).
         */
        private String selectionCondition;

        /**
         * Optional condition indicating whether to skip the resource move(s).
         */
        private String skipMoveCondition;

        private OwnerType ownerType;

        /**
         * The specific object that has seized the resources to be moved.
         */
        private IntelligentObject ownerObject;
    }

}
