package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fixed.FixedRunSpace;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NodeRunSpace extends FixedRunSpace {
    private List<NetworkWrapper> networkWrappers = new ArrayList<>();
    private ExternalNode externalNode;
    private RoutingTravelers routingTravelers;
    private NodeRunSpace nearestNode;
    private NodeRunSpace inputNode;
    private NodeRunSpace nearestOutputNode;

    private int int_3;
    private NetworkWrapper networkWrapper;

    public NodeRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject, MayApplication application,
                        IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public BaseQueueGridItemTrace<IneligiblePickupSelection> RidePickupQueue() {
        return (BaseQueueGridItemTrace<IneligiblePickupSelection>) this.absBaseTraces[5];
    }

    public void addNetworkWrapper(NetworkWrapper networkWrapper) {
        this.networkWrappers.add(networkWrapper);
    }

    public NetworkWrapper getNetworkWrapper(NetworkElementRunSpace networkElementRunSpace) {
        if (networkElementRunSpace != null) {
            for (NetworkWrapper networkWrapper : this.networkWrappers) {
                if (networkWrapper.getNetworkElementRunSpace() == networkElementRunSpace) {
                    return networkWrapper;
                }
            }
        }
        return null;
    }

    public void removeNetworkWrapper(NetworkWrapper networkWrapper) {
        this.networkWrappers.remove(networkWrapper);
    }

    public boolean haveInputNode() {
        return this.externalNode != null && this.externalNode.inputNode();
    }

    public boolean haveOutputNode() {
        return this.externalNode != null && this.externalNode.outputNode();
    }

    public ExternalNode getExternalNode() {
        return this.externalNode;
    }

    public RoutingTravelers getRoutingTravelers() {
        if (this.routingTravelers == null) {
            this.routingTravelers = new RoutingTravelers(this);
        }
        return this.routingTravelers;
    }

    public NodeRunSpace getNearestNode() {
        if (this.int_3 < this.CurrentFacilityRunSpace().method_60()) {
            this.method_108();
        }
        return this.nearestNode;
    }

    public NodeRunSpace getInputNode() {
        if (this.int_3 < this.CurrentFacilityRunSpace().method_60()) {
            this.method_108();
        }
        return this.inputNode;
    }


    public NodeRunSpace getNearestOutputNode() {
        if (this.int_3 < this.CurrentFacilityRunSpace().method_60()) {
            this.method_108();
        }
        return this.nearestOutputNode;
    }

    private void method_108() {
        this.nearestNode = null;
        this.inputNode = null;
        this.nearestOutputNode = null;
        double num = Double.NaN;
        double num2 = Double.NaN;
        double num3 = Double.NaN;
        for (NodeRunSpace nodeRunSpace : this.CurrentFacilityRunSpace().NodeRunSpaces0) {
            if (nodeRunSpace != this && (this.getExternalNode() == null || nodeRunSpace.getExternalNode() == null || nodeRunSpace.getExternalNode().getFixedRunSpace() != this.getExternalNode().getFixedRunSpace())) {
                Direction direction = this.Location().sub(nodeRunSpace.Location());
                double distance = direction.x * direction.x + direction.y * direction.y + direction.z * direction.z;
                if (this.nearestNode == null || distance < num) {
                    this.nearestNode = nodeRunSpace;
                    num = distance;
                }
                if (nodeRunSpace.haveInputNode()) {
                    if (this.inputNode == null || distance < num2) {
                        this.inputNode = nodeRunSpace;
                        num2 = distance;
                    }
                } else if (nodeRunSpace.haveOutputNode() && (this.nearestOutputNode == null || distance < num3)) {
                    this.nearestOutputNode = nodeRunSpace;
                    num3 = distance;
                }
            }
        }
        this.int_3 = this.CurrentFacilityRunSpace().method_60();

    }

    public NetworkWrapper getInboundNetworkWrapper() {
        return this.networkWrapper;
    }

    public RideStation ParkingStation() {
        return (RideStation) this.AbsBaseRunSpaces[0];
    }
}
