package com.zdpx.cctpp.step;

import com.zdpx.cctpp.concrete.Expression;
import com.zdpx.cctpp.concrete.node.Node;
import com.zdpx.cctpp.element.ConstraintLogic;
import com.zdpx.cctpp.enu.NodeSelectionGoal;
import lombok.Getter;
import lombok.Setter;

/**
 * The <b>Route</b> step may be used with a RoutingGroup element to route an entity object to a destination selected
 * from a list of candidate nodes.
 * <p>
 * It is also possible to reserve materials and resources for consumption at an upstream node prior to routing to the
 * destination node. This is done through the <i>Route Constraint Logic</i> repeat group and related Constraint Logic
 * element.
 * <p>
 * The Required Materials repeat group on the Route step is now deprecated and no longer displayed in new models,
 * replaced by the new Route Constraint Logic repeat group. The Route Constraint Logic repeat group allows a user to
 * easily separate different constraint types into multiple Constraint Logic element references and thus tables
 * (e.g., separate tables defining the material and secondary resource availability requirements for a Route step).
 */
@Setter
@Getter
public class Route extends Step {

    /**
     * The name of the routing group from which a destination node will be selected.
     */
    private String routingGroupName;

    /**
     * The goal used to select a destination node from the routing group.
     */
    private NodeSelectionGoal selectionGoalNode;

    /**
     * Optional node whose location will be used for the ‘distance to’ calculation instead of the routing entity,
     * used with SmallestDistance or LargestDistance selection goal.
     */
    private Node sourceNode;

    /**
     * The expression criteria, evaluated for each candidate destination, used with a Smallest Value or Largest Value
     * selection goal. Use the keyword {@link Candidate} at the beginning of the expression.
     */
    private Expression valueExpression;

    /**
     * An optional condition evaluated for each candidate destination that must be true for the destination node to
     * be selected. Use the keyword Candidate at the beginning of the expression.
     */
    private String selectionCondition;

    /**
     * Constraint logic used to enforce additional constraints on the entity's route request.
     */
    private ConstraintLogic routeConstraintLogic;

    /**
     * The name of the Constraint Logic element used to enforce additional constraints on the entity's route request.
     */
    private String constraintLogicName;

    /**
     * The random number stream to be used if the destination Selection Goal is 'Random'.
     */
    private String randomNumberStream;

}
