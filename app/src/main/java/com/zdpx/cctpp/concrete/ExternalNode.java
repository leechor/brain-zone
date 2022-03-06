package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fixed.FixedRunSpace;
import com.zdpx.cctpp.concrete.node.Node;

/**
 *
 */
public class ExternalNode {
    private NodeRunSpace nodeRunSpace;
    private NodeClassProperty nodeClassProperty;
    private NodeRunSpace _nodeRunSpace1;
    private RideStation rideStation;
    private ContainerElementRunSpace _containerElementRunSpace0;
    private FixedRunSpace fixedRunSpace;
    private boolean isInputNode;
    private boolean isOutputNode;
    private NodeInputLogicType nodeInputLogicType;

    public ExternalNode(NodeRunSpace nodeRunSpace, FixedRunSpace fixedRunSpace) {
        this.nodeRunSpace = nodeRunSpace;
        this.nodeClassProperty = ((Node) (nodeRunSpace.myElementInstance)).NodeClassProperty;
        this.fixedRunSpace = fixedRunSpace;
        if (this.fixedRunSpace.NodeRunSpaces == null || this.nodeClassProperty.index >= this.fixedRunSpace.NodeRunSpaces.length) {
            NodeRunSpace[] nodeRunSpaces = this.fixedRunSpace.NodeRunSpaces;
            this.fixedRunSpace.NodeRunSpaces = new NodeRunSpace[this.nodeClassProperty.index + 1];
            if (nodeRunSpaces != null) {
                System.arraycopy(nodeRunSpaces, 0, this.fixedRunSpace.NodeRunSpaces, 0, nodeRunSpaces.length);
            }
        }
        this.fixedRunSpace.NodeRunSpaces[this.nodeClassProperty.index] = this.nodeRunSpace;
        this.nodeInputLogicType =
                (NodeInputLogicType) this.nodeClassProperty.getEnumPropertyRow().getObjectValue(this.fixedRunSpace,
                        this.fixedRunSpace.ParentObjectRunSpace);
        switch (this.nodeInputLogicType) {
            case Node:
                if (this.nodeClassProperty.getNodeSequenceDestinationPropertyRow().isInvalidObjectNameValue(this.nodeRunSpace, this.fixedRunSpace) == 1.0) {
                    this.isInputNode = true;
                    if (this.nodeClassProperty.getNodeSequenceDestinationPropertyRow().getNode() != null) {
                        this._nodeRunSpace1 =
                                ElementRunSpaceOperator.getElementRunSpace(NodeRunSpace.class, this.nodeRunSpace,
                                        this.nodeClassProperty.getNodeSequenceDestinationPropertyRow(),
                                        this.fixedRunSpace);
                    }
                }
                break;
            case Station:
                if (this.nodeClassProperty.getStationElementProperty().isInvalidObjectNameValue(this.nodeRunSpace,
                        this.fixedRunSpace) == 1.0) {
                    this.isInputNode = true;
                    if (this.nodeClassProperty.getStationElementProperty().getObjectValue() != null) {
                        this.rideStation = ElementRunSpaceOperator.getElementRunSpace(RideStation.class,
                                this.nodeRunSpace,
                                this.nodeClassProperty.getStationElementProperty(), this.fixedRunSpace);
                    }
                }
                break;
            case Container:
                if (this.nodeClassProperty.getContainerElementProperty().isInvalidObjectNameValue(this.nodeRunSpace,
                        this.fixedRunSpace) == 1.0) {
                    this.isInputNode = true;
                    if (this.nodeClassProperty.getContainerElementProperty().getObjectValue() != null) {
                        this._containerElementRunSpace0 =
                                ElementRunSpaceOperator.getElementRunSpace(ContainerElementRunSpace.class,
                                        this.nodeRunSpace,
                                        this.nodeClassProperty.getContainerElementProperty(), this.fixedRunSpace);
                    }
                }
                break;
        }
        this.isOutputNode = !this.isInputNode;
    }

    public FixedRunSpace getFixedRunSpace() {
        return this.fixedRunSpace;
    }

    public boolean inputNode() {
        return this.isInputNode;
    }

    public boolean outputNode() {
        return this.isOutputNode;
    }

    public NodeInputLogicType InputLocationType() {
        return this.nodeInputLogicType;
    }

    public RideStation getAssociatedStation(boolean b1, boolean b2) {
        switch (this.nodeInputLogicType) {
            case Node:
                if (b1) {
                    NodeRunSpace runSpace = this.getNodeRunSpace();
                    if (runSpace.haveInputNode()) {
                        return runSpace.getExternalNode().getAssociatedStation(b1, b2);
                    }
                }
                return null;
            case Station: {
                RideStation rs = (this.rideStation != null) ? this.rideStation :
                        ElementRunSpaceOperator.getElementRunSpace(RideStation.class, this.nodeRunSpace,
                                this.nodeClassProperty.getStationElementProperty(), this.fixedRunSpace);
                if (b2 && rs.getAssociatedStation() != null) {
                    return rs.getAssociatedStation();
                }
                return rs;
            }
            default:
                return null;
        }
    }

    private NodeRunSpace getNodeRunSpace() {
        if (this.nodeInputLogicType == NodeInputLogicType.Node) {
            return (this._nodeRunSpace1 != null) ? this._nodeRunSpace1 :
                    ElementRunSpaceOperator.getElementRunSpace(NodeRunSpace.class, this.nodeRunSpace,
                            this.nodeClassProperty.getNodeSequenceDestinationPropertyRow(), this.fixedRunSpace);
        }
        return null;
    }

    public RideStation getAssociatedStation() {
        return this.getAssociatedStation(false, true);
    }
}
