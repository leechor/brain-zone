package com.zdpx.cctpp.concrete.node;

import com.zdpx.cctpp.concrete.ActiveModel;
import com.zdpx.cctpp.concrete.InternalReference;
import com.zdpx.cctpp.concrete.NodeDefinition;

/**
 * A <b>TransferNode</b> is a more comprehensive node that supports connection to paths as well as the ability to select
 * destination, path, and transfer device. Most objects that support outgoing connections have an associated (embedded)
 * transfer node.
 * <br>
 * Because transporters commonly travel through nodes, the state assignments' functionality provided by the
 * <i>BasicNode</i> and <i>TransferNode</i> objects make it easy for a user to distinguish between entity or transporter
 * state assignments by providing an <i>Assign If</i> condition option. <b>Node that</b> these available assign
 * condition type choice(Custom Condition, Entity Entering, No Condition, Transporter Entering) are also available
 * within the tally statistic using the node's Tally Statistics -> On Entering repeat group.
 * <br>
 */
public class TransferNode {

    public static void init(InternalReference internalReference, ActiveModel activeModel,
							NodeDefinition nodeDefinition) {
        NodeDefinition definition = new NodeDefinition("TransferNode", activeModel, nodeDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        TransferNode.init(definition);
    }

    public static void init(NodeDefinition nodeDefinition) {

    }

}
