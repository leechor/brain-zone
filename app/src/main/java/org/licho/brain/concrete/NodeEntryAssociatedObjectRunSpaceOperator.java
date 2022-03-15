package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;

/**
 *
 */
public class NodeEntryAssociatedObjectRunSpaceOperator {

    public NodeEntryAssociatedObjectRunSpace init(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace,
                                                  NodeRunSpace nodeRunSpace) {
        NodeEntryAssociatedObjectRunSpace entryAssociatedObjectRunSpace;
        if (this.nodeEntryAssociatedObjectRunSpace == null) {
            entryAssociatedObjectRunSpace = new NodeEntryAssociatedObjectRunSpace();
        } else {
            entryAssociatedObjectRunSpace = this.nodeEntryAssociatedObjectRunSpace;
            this.nodeEntryAssociatedObjectRunSpace =
                    entryAssociatedObjectRunSpace.getNodeEntryAssociatedObjectRunSpace();
            entryAssociatedObjectRunSpace.setNodeEntryAssociatedObjectRunSpace(null);
        }
        entryAssociatedObjectRunSpace.init(tokenRunSpace, entityRunSpace, nodeRunSpace);
        return entryAssociatedObjectRunSpace;
    }

    public void setNodeEntryAssociatedObjectRunSpace(NodeEntryAssociatedObjectRunSpace nodeEntryAssociatedObjectRunSpace) {
        nodeEntryAssociatedObjectRunSpace.clear();
        nodeEntryAssociatedObjectRunSpace.setNodeEntryAssociatedObjectRunSpace(this.nodeEntryAssociatedObjectRunSpace);
        this.nodeEntryAssociatedObjectRunSpace = nodeEntryAssociatedObjectRunSpace;
    }

    private NodeEntryAssociatedObjectRunSpace nodeEntryAssociatedObjectRunSpace;

}
